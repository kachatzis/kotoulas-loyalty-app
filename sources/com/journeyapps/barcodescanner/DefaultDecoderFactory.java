package com.journeyapps.barcodescanner;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

public class DefaultDecoderFactory implements DecoderFactory {
    private String characterSet;
    private Collection<BarcodeFormat> decodeFormats;
    private Map<DecodeHintType, ?> hints;

    public DefaultDecoderFactory(Collection<BarcodeFormat> collection, Map<DecodeHintType, ?> map, String str) {
        this.decodeFormats = collection;
        this.hints = map;
        this.characterSet = str;
    }

    public Decoder createDecoder(Map<DecodeHintType, ?> map) {
        Map enumMap = new EnumMap(DecodeHintType.class);
        enumMap.putAll(map);
        map = this.hints;
        if (map != null) {
            enumMap.putAll(map);
        }
        if (this.decodeFormats != null) {
            enumMap.put(DecodeHintType.POSSIBLE_FORMATS, this.decodeFormats);
        }
        if (this.characterSet != null) {
            enumMap.put(DecodeHintType.CHARACTER_SET, this.characterSet);
        }
        map = new MultiFormatReader();
        map.setHints(enumMap);
        return new Decoder(map);
    }
}
