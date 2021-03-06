package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TcpServer {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();

			// 1-1. time-wait 시간에 소켓에 포트 번호 할당을 가능하게 하기 위해서
			serverSocket.setReuseAddress(true);
			
			// 2. 바인딩
			// : Socket에 SocketAddress(IPAddress + Port)를 바인딩 한다.

			// 가상머신 없다면 잘 작동한당
//			InetAddress inetAddress = InetAddress.getLocalHost();
//			String localHost = inetAddress.getHostAddress();
//			serverSocket.bind(new InetSocketAddress(inetAddress, 5000));
//			serverSocket.bind(new InetSocketAddress(localHost, 5000));

			serverSocket.bind(new InetSocketAddress("0.0.0.0", 5000));

			// 3. accept
			// : 클라이언트의 연결요청을 기다린다.
			Socket socket = serverSocket.accept(); // blocking

			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remoteHostPort = inetRemoteSocketAddress.getPort();

			System.out.println("connected by client [ " + remoteHostAddress + " : " + remoteHostPort + " ]");

			try {
				// 4. IO Stream 받아오기.
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				while (true) {
					// 5. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer);
					if (readByteCount == -1) {
						// 클라이언트 정상종료
						System.out.println("[server] closed by client");
						break;
					}

					String data = new String(buffer, 0, readByteCount, "utf-8");
					System.out.println("[server] received : " + data);

					// 6. 데이터 쓰기
//					os.write(data.getBytes("utf-8"));
					
					//6-1. 클라이언트 time out test 
					//			없어도 데이터를 보내지 않으니 같은 결과 ??
//					try {
//						Thread.sleep(2000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}
			} catch (SocketException e) {
				System.out.println("[server] sudden closed by client");

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (socket != null && socket.isClosed()) {
						socket.close();
					}
				} catch (IOException e) {
					e.getStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
