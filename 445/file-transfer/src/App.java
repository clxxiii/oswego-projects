import packet.*;

public class App {
    public static void main(String[] args) throws Exception {
        Packet packet = new DataPacket(30, new byte[1]);
        byte[] bytes = packet.toBytes();
        System.out.println(bytes);
    }
}
