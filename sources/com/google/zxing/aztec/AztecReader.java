package com.google.zxing.aztec;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.aztec.decoder.Decoder;
import com.google.zxing.aztec.detector.Detector;
import com.google.zxing.common.DecoderResult;
import java.util.Map;

public final class AztecReader implements Reader {
    public void reset() {
    }

    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, null);
    }

    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        ResultPoint[] points;
        ResultPoint[] resultPointArr;
        NotFoundException notFoundException;
        AztecDetectorResult detect;
        ResultPointCallback resultPointCallback;
        int length;
        ReaderException e;
        Detector detector = new Detector(binaryBitmap.getBlackMatrix());
        binaryBitmap = null;
        DecoderResult decoderResult = null;
        NotFoundException notFoundException2;
        try {
            AztecDetectorResult detect2 = detector.detect(false);
            points = detect2.getPoints();
            try {
                DecoderResult decode = new Decoder().decode(detect2);
                resultPointArr = points;
                notFoundException2 = null;
                decoderResult = decode;
                notFoundException = notFoundException2;
            } catch (NotFoundException e2) {
                notFoundException = e2;
                resultPointArr = points;
                notFoundException2 = null;
                if (decoderResult == null) {
                    try {
                        detect = detector.detect(true);
                        resultPointArr = detect.getPoints();
                        decoderResult = new Decoder().decode(detect);
                    } catch (NotFoundException e3) {
                        binaryBitmap = e3;
                        if (notFoundException == null) {
                            throw notFoundException;
                        } else if (notFoundException2 == null) {
                            throw notFoundException2;
                        } else {
                            throw binaryBitmap;
                        }
                    } catch (FormatException e4) {
                        binaryBitmap = e4;
                        if (notFoundException == null) {
                            throw notFoundException;
                        } else if (notFoundException2 == null) {
                            throw binaryBitmap;
                        } else {
                            throw notFoundException2;
                        }
                    }
                }
                if (map != null) {
                    resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                    if (resultPointCallback != null) {
                        length = resultPointArr.length;
                        while (binaryBitmap < length) {
                            resultPointCallback.foundPossibleResultPoint(resultPointArr[binaryBitmap]);
                            binaryBitmap++;
                        }
                    }
                }
                binaryBitmap = new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr, BarcodeFormat.AZTEC);
                map = decoderResult.getByteSegments();
                if (map != null) {
                    binaryBitmap.putMetadata(ResultMetadataType.BYTE_SEGMENTS, map);
                }
                map = decoderResult.getECLevel();
                if (map != null) {
                    binaryBitmap.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, map);
                }
                return binaryBitmap;
            } catch (FormatException e5) {
                e = e5;
                resultPointArr = points;
                notFoundException2 = e;
                notFoundException = null;
                if (decoderResult == null) {
                    detect = detector.detect(true);
                    resultPointArr = detect.getPoints();
                    decoderResult = new Decoder().decode(detect);
                }
                if (map != null) {
                    resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                    if (resultPointCallback != null) {
                        length = resultPointArr.length;
                        while (binaryBitmap < length) {
                            resultPointCallback.foundPossibleResultPoint(resultPointArr[binaryBitmap]);
                            binaryBitmap++;
                        }
                    }
                }
                binaryBitmap = new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr, BarcodeFormat.AZTEC);
                map = decoderResult.getByteSegments();
                if (map != null) {
                    binaryBitmap.putMetadata(ResultMetadataType.BYTE_SEGMENTS, map);
                }
                map = decoderResult.getECLevel();
                if (map != null) {
                    binaryBitmap.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, map);
                }
                return binaryBitmap;
            }
        } catch (NotFoundException e6) {
            notFoundException = e6;
            points = null;
            resultPointArr = points;
            notFoundException2 = null;
            if (decoderResult == null) {
                detect = detector.detect(true);
                resultPointArr = detect.getPoints();
                decoderResult = new Decoder().decode(detect);
            }
            if (map != null) {
                resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                if (resultPointCallback != null) {
                    length = resultPointArr.length;
                    while (binaryBitmap < length) {
                        resultPointCallback.foundPossibleResultPoint(resultPointArr[binaryBitmap]);
                        binaryBitmap++;
                    }
                }
            }
            binaryBitmap = new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr, BarcodeFormat.AZTEC);
            map = decoderResult.getByteSegments();
            if (map != null) {
                binaryBitmap.putMetadata(ResultMetadataType.BYTE_SEGMENTS, map);
            }
            map = decoderResult.getECLevel();
            if (map != null) {
                binaryBitmap.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, map);
            }
            return binaryBitmap;
        } catch (FormatException e7) {
            e = e7;
            points = null;
            resultPointArr = points;
            notFoundException2 = e;
            notFoundException = null;
            if (decoderResult == null) {
                detect = detector.detect(true);
                resultPointArr = detect.getPoints();
                decoderResult = new Decoder().decode(detect);
            }
            if (map != null) {
                resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                if (resultPointCallback != null) {
                    length = resultPointArr.length;
                    while (binaryBitmap < length) {
                        resultPointCallback.foundPossibleResultPoint(resultPointArr[binaryBitmap]);
                        binaryBitmap++;
                    }
                }
            }
            binaryBitmap = new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr, BarcodeFormat.AZTEC);
            map = decoderResult.getByteSegments();
            if (map != null) {
                binaryBitmap.putMetadata(ResultMetadataType.BYTE_SEGMENTS, map);
            }
            map = decoderResult.getECLevel();
            if (map != null) {
                binaryBitmap.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, map);
            }
            return binaryBitmap;
        }
        if (decoderResult == null) {
            detect = detector.detect(true);
            resultPointArr = detect.getPoints();
            decoderResult = new Decoder().decode(detect);
        }
        if (map != null) {
            resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
            if (resultPointCallback != null) {
                length = resultPointArr.length;
                while (binaryBitmap < length) {
                    resultPointCallback.foundPossibleResultPoint(resultPointArr[binaryBitmap]);
                    binaryBitmap++;
                }
            }
        }
        binaryBitmap = new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr, BarcodeFormat.AZTEC);
        map = decoderResult.getByteSegments();
        if (map != null) {
            binaryBitmap.putMetadata(ResultMetadataType.BYTE_SEGMENTS, map);
        }
        map = decoderResult.getECLevel();
        if (map != null) {
            binaryBitmap.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, map);
        }
        return binaryBitmap;
    }
}
