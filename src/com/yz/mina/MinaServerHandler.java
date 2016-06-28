package com.yz.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.yz.utils.DataConvertor;

public class MinaServerHandler implements IoHandler {

	public void exceptionCaught(IoSession arg0, Throwable arg1) throws Exception {

	}

	public void inputClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		arg0.closeOnFlush();
	}

	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub

		if(message instanceof IoBuffer)
		{
			System.out.println("message is "+message);
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

		for (int j = 0; j < d_data.length; j++)
			System.out.println(d_data[j]);

		if (d_data[2].equals('A')) {
			String number = d_data[1]; // 设备编号
			String latitude = d_data[3]; // 维度
			String longitude = d_data[5]; // 经度
			String voltage = d_data[7]; // 电压
			String temperature = d_data[8]; // 温度
			String state = d_data[9]; // 工作状态 A=正常工作，D=关机，R=重启中，N=未知状态
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
