import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.text.ParseException;
import java.util.HashMap;

import packet.*;

public class PacketTests {

  @Test
  public void ackPacketTest() {
    AckPacket packet = new AckPacket(17);
    assertEquals(packet.blockNumber, 17);
    assertEquals(packet.opcode, Opcode.ACK);
    ByteBuffer data = packet.toBuffer();
    try {
      packet = (AckPacket) Packet.parse(data);
    } catch (ParseException e) {
      fail("Parse threw an error");
    }
    assertEquals(packet.blockNumber, 17);
    assertEquals(packet.opcode, Opcode.ACK);
  }

  @Test
  public void ackPacketOptionsTest() {
    HashMap<String, String> options = new HashMap<>();
    options.put("example_key", "Example Value!");

    AckPacket packet = new AckPacket(17, options);
    assertEquals(packet.blockNumber, 17);
    assertEquals(packet.opcode, Opcode.ACK);
    ByteBuffer data = packet.toBuffer();
    try {
      packet = (AckPacket) Packet.parse(data);
    } catch (ParseException e) {
      fail("Parse threw an error");
    }
    assertEquals(packet.blockNumber, 17);
    assertEquals(packet.opcode, Opcode.ACK);

    HashMap<String, String> packetOptions = packet.options;
    assertEquals(packetOptions.get("example_key"), options.get("example_key"));
  }

  @Test
  public void dataPacketTest() {
    String message = "Hello World!";
    byte[] msgBytes = message.getBytes();
    DataPacket packet = new DataPacket(17, msgBytes);
    assertEquals(packet.blockNumber, 17);
    assertEquals(packet.opcode, Opcode.DATA);
    assertTrue(packet.data.equals(msgBytes));
    ByteBuffer data = packet.toBuffer();
    try {
      packet = (DataPacket) Packet.parse(data);
    } catch (ParseException e) {
      fail("Parse threw an error");
    }
    assertEquals(packet.blockNumber, 17);
    assertEquals(packet.opcode, Opcode.DATA);
    assertEquals(new String(packet.data), message);
  }

  @Test
  public void errorPacketTest() {
    String msg = "You don't have permissions to access that file.";
    ErrorPacket packet = new ErrorPacket(ErrorCode.FILE_NOT_FOUND, msg);
    assertEquals(packet.opcode, Opcode.ERROR);
    assertEquals(packet.errorCode, ErrorCode.FILE_NOT_FOUND);
    assertEquals(packet.errorMsg, msg);
    ByteBuffer data = packet.toBuffer();
    try {
      packet = (ErrorPacket) Packet.parse(data);
    } catch (ParseException e) {
      fail("Parse threw an error");
    }
    assertEquals(packet.opcode, Opcode.ERROR);
    assertEquals(packet.errorCode, ErrorCode.FILE_NOT_FOUND);
    assertEquals(packet.errorMsg, msg);
  }

  @Test
  public void requestPacketTest() {
    Opcode code = Opcode.RRQ;
    String file = "HelloWorld.java";
    String mode = "octet";
    RequestPacket packet = new RequestPacket(code, file, mode);
    assertEquals(packet.opcode, code);
    assertEquals(packet.fileName, file);
    assertEquals(packet.mode, mode);
    ByteBuffer data = packet.toBuffer();
    try {
      packet = (RequestPacket) Packet.parse(data);
    } catch (ParseException e) {
      fail("Parse threw an error");
    }
    assertEquals(packet.opcode, code);
    assertEquals(packet.fileName, file);
    assertEquals(packet.mode, mode);
  }

  @Test
  public void requestOptionsPacketTest() {
    Opcode code = Opcode.WRQ;
    String file = "HelloWorld.java";
    String mode = "octet";
    HashMap<String, String> options = new HashMap<>();
    options.put("example_key", "Example Value!");
    RequestPacket packet = new RequestPacket(code, file, mode, options);
    assertEquals(packet.opcode, code);
    assertEquals(packet.fileName, file);
    assertEquals(packet.mode, mode);
    ByteBuffer data = packet.toBuffer();
    try {
      packet = (RequestPacket) Packet.parse(data);
    } catch (ParseException e) {
      fail("Parse threw an error");
    }
    assertEquals(packet.opcode, code);
    assertEquals(packet.fileName, file);
    assertEquals(packet.mode, mode);

    HashMap<String, String> packetOptions = packet.options;
    assertEquals(packetOptions.get("example_key"), options.get("example_key"));
  }
}
