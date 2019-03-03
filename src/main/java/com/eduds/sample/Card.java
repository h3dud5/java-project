package com.eduds.sample;

import java.io.Serializable;

public class Card implements Serializable {
    String cardNumber;
    String status;
    String blockCode;

@Override
    public String toString(){
      return String.format("%s, %s, %s", cardNumber, status, blockCode);
}

}
