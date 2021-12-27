package Zorkgreus;

public class Key extends Item {
  private String keyId;

  public Key(String keyId, String keyName, int weight) {
    super(keyName, keyName, weight, keyName);
    this.keyId = keyId;
  }

  public String getKeyId() {
    return keyId;
  }
}