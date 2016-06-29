package com.yz.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yz.po.Camera;
import com.yz.service.CameraService;
import com.yz.utils.DataConvertor;

public class MinaServerHandler implements IoHandler {

	@Autowired
	private CameraService cameraService;

	public void exceptionCaught(IoSession arg0, Throwable arg1) throws Exception {

	}

	public void inputClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		arg0.closeOnFlush();
	}

	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		
//		byte[] byteArray={1,1,1,1,1,1,1,1};
//		
//		session.write(IoBuffer.wrap(byteArray));

		if (message instanceof IoBuffer) {
			System.out.println("message is " + message);
		}

		byte[] m_oData = DataConvertor.toByteArray(message);
		char[] b = new char[m_oData.length];
		StringBuffer tStringBuf = new StringBuffer();
		for (int i = 0; i < m_oData.length; i++) {
			b[i] = (char) m_oData[i];
		}
		tStringBuf.append(b);
		String s_data = tStringBuf.toString();
		// String s_data = DataConvertor.toHexString(m_oData);

		System.out.println(s_data);

		String[] d_data = s_data.split(",");

		if (d_data[2].equals("V")) {
			String number = d_data[1]; // 设备编号
			String s_latitude = d_data[3]; // 维度
			String latitude = DataConvertor.stringTolatitude(s_latitude);
			String s_longitude = d_data[5]; // 经度
			String longitude = DataConvertor.stringTolongitude(s_longitude);
			String voltage = d_data[7]; // 电压
			String temperature = d_data[8]; // 温度
			String state = "";
			if (d_data.length > 9) {
				state = d_data[9]; // 工作状态  A=正常工作(1)，D=关机(0)，R=重启中(2)，N=未知状态(-1)
			}

			String head = "$COMMAND,";
			
			String command = ",T";
			String backup = ",000";
			String blank = " ";
			String enter = "\n";
			
			String send = head+number+command+backup+blank+enter;
			
			byte[] srtbyte = send.getBytes();
			
			session.write(IoBuffer.wrap(srtbyte));
			
			Camera camera = cameraService.findCameraByNumber(number);
			
			System.out.println("state is "+state);

			if (camera == null) {
				camera = new Camera();
				camera.setCnumber(number);
				camera.setLat(latitude);
				camera.setLng(longitude);
				camera.setVoltage(voltage);
				camera.setTemperature(temperature);

				if (d_data.length > 9) {
					if (state.contains("A")) {
						camera.setStatus(1);
					} else if (state.contains("D")) {
						camera.setStatus(0);
					} else if (state.contains("R")) {
						camera.setStatus(2);
					} else if (state.contains("N")) {
						camera.setStatus(-1);
					}
					System.out.println("insert camera status is "+camera.getStatus());
					cameraService.insertCamera(camera);
				}

			} else {

				camera.setCnumber(number);
				camera.setLat(latitude);
				camera.setLng(longitude);
				camera.setVoltage(voltage);
				camera.setTemperature(temperature);

				if (d_data.length > 9) {
					if (state.contains("A")) {
						camera.setStatus(1);
					} else if (state.contains("D")) {
						camera.setStatus(0);
					} else if (state.contains("R")) {
						camera.setStatus(2);
					} else if (state.contains("N")) {
						camera.setStatus(-1);
					}
					System.out.println("update camera status is "+camera.getStatus());
					cameraService.updateCamera(camera);
				}

			}
		}

	}

	public void messageSent(IoSession arg0, Object arg1) throws Exception {
		System.out.println("发送信息:" + arg1.toString() + "到" + arg0.getRemoteAddress().toString());
	}

	public void sessionClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		arg0.closeOnFlush();
	}

	public void sessionCreated(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("opened " + session.getRemoteAddress().toString());
	}

}
