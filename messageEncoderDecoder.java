package bgu.spl.net;

import bgu.spl.net.api.MessageEncoderDecoder;

public class messageEncoderDecoder implements MessageEncoderDecoder {
    @Override
    public Object decodeNextByte(byte nextByte) {
        return null;
    }

    @Override
    public byte[] encode(Object message) {
        return new byte[0];
    }
}
