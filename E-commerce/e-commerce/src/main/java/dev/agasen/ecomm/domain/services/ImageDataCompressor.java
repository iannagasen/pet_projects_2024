package dev.agasen.ecomm.domain.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import dev.agasen.ecomm.domain.entity.CompressedImage;
import dev.agasen.ecomm.domain.entity.Image;

public final class ImageDataCompressor {

  public static Compressor withImage(Image image) {
    return new Compressor(image);
  }


  public static Decompressor withCompressedImage(CompressedImage image) {
    return new Decompressor(image);
  }


  public record Compressor(Image image) {
    public CompressedImage compress() {
      return new CompressedImage(image.id(), image.name(), image.type(), compressBytes(image.imageData()));
    }
  }


  public record Decompressor(CompressedImage image) {
    public Image decompress() {
      return new Image(image.id(), image.name(), image.type(), decompressBytes(image.imageData()));
    }
  }


  public static byte[] compressBytes(byte[] data) {
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


  public static byte[] decompressBytes(byte[] data) {
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


  private ImageDataCompressor() {}

}
