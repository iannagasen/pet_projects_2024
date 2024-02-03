package dev.agasen.imageupload;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.stereotype.Service;

@Service
public class ImageCompressionService {
  
  public byte[] compress(byte[] data) {
    /**
     * compress the image data
     */
    Deflater deflater = new Deflater();
    deflater.setLevel(Deflater.BEST_COMPRESSION);
    deflater.setInput(data);
    deflater.finish();

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
    byte[] tmp = new byte[4*1024];
    while(!deflater.finished()) {
      int size = deflater.deflate(tmp);
      outputStream.write(tmp, 0, size);
    }

    try {
      outputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return outputStream.toByteArray();
  }

  public byte[] decompress(byte[] data) {
    Inflater inflater = new Inflater();
    inflater.setInput(data);
    var outputStream = new ByteArrayOutputStream(data.length);
    byte[] tmp = new byte[4 * 1024];
    try {
      while (!inflater.finished()) {
        int count = inflater.inflate(tmp);
        outputStream.write(tmp, 0, count);
      }
      outputStream.close();
    } catch (DataFormatException | IOException e) {
      e.printStackTrace();
    }
    return outputStream.toByteArray();
  }
  
}
