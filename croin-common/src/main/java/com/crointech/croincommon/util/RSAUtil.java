package com.crointech.croincommon.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

/**
 * RSA 是非对称的密码算法，密钥分公钥和私钥，公钥用来加密，私钥用于解密
 */
@Slf4j
public class RSAUtil {

    /**
     * 加密算法AES
     */
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * 算法名称/加密模式/数据填充方式
     * 默认：RSA/ECB/PKCS1Padding
     */
    private static final String ALGORITHMS = "RSA/ECB/PKCS1Padding";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * RSA 位数 如果采用2048 上面最大加密和最大解密则须填写:  245 256
     */
    private static final int INITIALIZE_LENGTH = 1024;

    /**
     * 生成密钥对：密钥对中包含公钥和私钥
     *
     * @return 包含 RSA 公钥与私钥的 keyPair
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static KeyPair getKeyPair() throws NoSuchAlgorithmException {
        // 获得RSA密钥对的生成器实例
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        // 说的一个安全的随机数
        SecureRandom secureRandom =
                new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8));
        // 这里可以是1024、2048 初始化一个密钥对
        keyPairGenerator.initialize(INITIALIZE_LENGTH, secureRandom);
        // 获得密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    /**
     * 获取公钥 (并进行Base64编码，返回一个 Base64 编码后的字符串)
     *
     * @param keyPair
     * @return 返回一个 Base64 编码后的公钥字符串
     */
    public static String getPublicKey(KeyPair keyPair) {
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return new String(Base64.encodeBase64(bytes));
    }

    /**
     * 获取私钥(并进行Base64编码，返回一个 Base64 编码后的字符串)
     *
     * @param keyPair
     * @return 返回一个 Base64 编码后的私钥字符串
     */
    public static String getPrivateKey(KeyPair keyPair) {
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return new String(Base64.encodeBase64(bytes));
    }

    /**
     * 将Base64编码后的公钥转换成 PublicKey 对象
     *
     * @param pubStr
     * @return PublicKey
     */
    public static PublicKey string2PublicKey(String pubStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bytes = Base64.decodeBase64(pubStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 将Base64编码后的私钥转换成 PrivateKey 对象
     *
     * @param priStr
     * @return PrivateKey
     */
    public static PrivateKey string2Privatekey(String priStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bytes = Base64.decodeBase64(priStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 公钥加密
     *
     * @param content   待加密的内容 byte[]
     * @param publicKey 加密所需的公钥对象 PublicKey
     * @return 加密后的字节数组 byte[]
     */
    public static byte[] publicEncrytype(byte[] content, PublicKey publicKey) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        byte[] bytes = cipher.doFinal(content);
//        return bytes;
        int inputLength = content.length;
        // 标识
        int offSet = 0;
        byte[] resultBytes = {};
        byte[] cache = {};
        while (inputLength - offSet > 0) {
            if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(content, offSet, MAX_ENCRYPT_BLOCK);
                offSet += MAX_ENCRYPT_BLOCK;
            } else {
                cache = cipher.doFinal(content, offSet, inputLength - offSet);
                offSet = inputLength;
            }
            resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
            System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
        }
        return resultBytes;
    }

    /**
     * 私钥解密
     *
     * @param content    待解密的内容 byte[]
     * @param privateKey 解密需要的私钥对象 PrivateKey
     * @return 解密后的字节数组 byte[]
     */
    public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        byte[] bytes = cipher.doFinal(content);
        int inputLength = content.length;
        // 标识
        int offSet = 0;
        byte[] resultBytes = {};
        byte[] cache = {};
        while (inputLength - offSet > 0) {
            if (inputLength - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(content, offSet, MAX_DECRYPT_BLOCK);
                offSet += MAX_DECRYPT_BLOCK;
            } else {
                cache = cipher.doFinal(content, offSet, inputLength - offSet);
                offSet = inputLength;
            }
            resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
            System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
        }
        return resultBytes;
    }
//
//    /**
//     * 公钥加密数据
//     * @param dataString 数据字符串
//     * @param publicKeyStr 公钥
//     * @return
//     */
//    public static BaseSelfReq encryptData(String dataString, String publicKeyStr) {
//        try {
//            String aesKey = AesUtil.getKey();
//            log.info("AES的key:{},length:{}", aesKey, aesKey.getBytes().length);
//
//            String aesData = AesUtil.encrypt(dataString, aesKey);
//            log.info("AesUtil加密后的数据:{}", aesData);
//
//            PublicKey publicKey = RSAUtil.string2PublicKey(publicKeyStr);
//            // 公钥加密/私钥解密
//            byte[] publicEncryBytes = RSAUtil.publicEncrytype(aesKey.getBytes(), publicKey);
//            String rsaData = new String(Base64.encodeBase64(publicEncryBytes));
//            log.info("RsaUtil加密后的data数据:{},length:{}", rsaData, publicEncryBytes.length);
//
//            BaseSelfReq encryptBean = new BaseSelfReq();
//            encryptBean.setAesData(aesData);
//            encryptBean.setRsaData(rsaData);
//
//            log.info("加密后的数据为:{}", JSONObject.toJSONString(encryptBean));
//            return encryptBean;
//        } catch (Exception e) {
//            log.error("加解密异常：{}", e);
//            throw new BusinessException(BusinessMsg.RSA0001);
//        }
//    }
//
//    /**
//     * 私钥解密数据
//     * @param req 请求对象
//     * @param privateKeyStr 私钥
//     * @return
//     */
//    public static String decryptData(BaseSelfReq req, String privateKeyStr) {
//        try {
//            PrivateKey privateKey = string2Privatekey(privateKeyStr);
//            log.info("公钥加密长度：{}", req.getRsaData().getBytes().length);
//            byte[] privateDecryBytes = privateDecrypt(Base64.decodeBase64(req.getRsaData()), privateKey);
//            String aesKey = new String(privateDecryBytes, StandardCharsets.UTF_8);
//            String decrypt = AesUtil.decrypt(req.getAesData(), aesKey);
//            log.info("解密密后的数据为:{}", decrypt);
//            return "{}".equals(decrypt)?"":decrypt;
//        } catch (Exception e) {
//            log.error("加解密异常：{}", e);
//            throw new BusinessException(BusinessMsg.RSA0001);
//        }
//    }

    public static void main(String[] args) throws Exception {
//        KeyPair keyPair = getKeyPair();
//        String privateKey = getPrivateKey(keyPair);
//        String publicKey = getPublicKey(keyPair);
//        System.out.println("privateKey: "+getPrivateKey(keyPair));
//        System.out.println("publicKey: "+getPublicKey(keyPair));
//        // 明文内容
//        String content = "{\"merchantsId\": 1,\"userId\": 1243927,\"activityId\": \"1b972d3dc4b549d3ba9e45902a8c2ad0\",\"account\": [{\"tradingGroupId\": \"429\",\"serverId\": 6,\"royaltyType\": 1,\"tradingAccount\": 707602437,\"throughTime\": \"2021-11-25 10:43:18\",\"institutionsGroupId\": 591}],\"userName\": \"Test user\",\"cnName\": \"userTest\",\"country\": \"China\",\"phone\": \"13312311231\",\"email\": \"test0902001@qq.com\"}";
//
//        System.out.println("原始字符串是：" + content);
//        BaseSelfReq baseSelfReq = encryptData(content, publicKey);
//        decryptData(baseSelfReq, privateKey);
//        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJd9lA3okyoT7AoKZtFp2yEpCVnDNGiTbLVntaa+/4aCvdd8JzYJ6zra3GoJ/K9MTgV0x2hdepGuLm+QSPbRWTPIH3Btg1favsRa/IQTehkLk67wsBD4SuP4LXVZZEZocAmMS6tYX8lgkzOSWkSglmAL1/XUVREUruB6ygS+kJjrAgMBAAECgYBJjFR3NDVOtoFglpA4l5Yl0VCS3ezx4LFkmUdd/1j7qzoUBljbKZGk72EXsmWVjgxLJdkyWEALvm8ZBwoFmrodYsMs7MPt/AuF0X+++MytO6rokKebx6KU3/of0IhGjmqHqUXcd/5L+O9bg1Q/UavkB6p4sUXYudRdnxz4QZQNMQJBAOHn+QPPbSJJvaO9dbMXUqcL+j5b/c2fguQofoYfZ8rJC9/cQaiyPqZjZFEqF6a/tuTT5qy9R30gpuf6wz8WhgUCQQCrq9OmZKTeESWzKZ5TrSZT26+ExZz25m5VR0+pSRfIj0V5vBMt7QHTqj8RsB05FaU2qzsqwjRo/ne5HmYmaGYvAkBe3Io0j2XEgDKiD2zdR23tt5ndlcN+FP1h0Z9PsuBixwm1d0gbDr5AIHx/mK1V2Ct064ZyAwmCAbQUtbhHRa4BAkAgZbz7meLDRdknhqMqh3LEQKFPHTwSHEqWLW8LM5AZeXBTGPtFn4TIvUhJvkLFkyAqwQXZe+0II33UobgFZ45hAkANCY2zoTn5sP9iqrRYfjFLVa07BFZd8bROK9bKb4Qm2Br9nnaw9CU7GOS4E3y7Lmm19ZhPRCPANen3PPydk7Mp";
//        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKllZHMbnxqdNHAp9IJQ3YwL5MYvCB/OiRhjE3jEkWImara7V7TbNwqATjN+iLdCE9LEnQwcgHLQ7hM6XIUq9VUR6LyLfej2alABh1YYe+Q7Q8fN//d7w+ew6CmxZz6hdl0KG28x9QBz+edFmQ3FLAQ/HeVKD2LN5/BKUq6uKrprAgMBAAECgYEAgSgmw8FOuWsdnVvP60Ce+3XiE92uUxoV27tHEHDH7M8f1H3ZLLNsLxyPA6I5uYivNqfkmmRs5543t/9qpbqQXPPjs6G6SimpisMi4K8x1t/bTyZdoTCbLLK8lsS4VuVMaR09ybyuXFS7o+Y1sBlQlQIhLrJfWTLYmJMjGbppsVECQQDV6eH/sXq/IGv9lFK77XSH2YKxY7vp/gmyoAX3pZkgP57Ujmg5heamJ3i+a2KbLIJgWa79GIuaWPdDALhqrIKzAkEAyrlPHIyXWFb6bQRoQHvb/bCjAtDz1BCgzCJ5+6KCgpsUC28KmGJZYdNZxlq5sYCqgjm9GUv4MuNuaYSLNQXlaQJAeR1NGipwpxpCSnbaSRDu86PRfYJMePWepAY6mral9j5PPWXL2RXZO6YJlTbTw6v1vXvJuf9Vu3Q3uzYDNFKsRQJBAJ5DpXuIVu9kmAVcF3egOtMHf3vHXVQF10MG92x4JqOIJlH20rK8tUMmGUQ9uBLXfEZGhVamdJJl8+D01SkD/WECQHLnSER8nwF+fkcYPy0oQzf+gLd/dJR+7d1rXaR4ntCRyeMQ8SPpkyUCiL7E8lxFIt0TafUMfWriwcco5sFx9JU=";
//        BaseSelfReq baseSelfReq = new BaseSelfReq();
//        baseSelfReq.setRsaData("XnKbg7bBbv4hAHDLtefSUUayqUaI7Irr0uc6WBqr0pC/g3LLdBsMRJgDh/omcJFHiDbPFqV/mfAMj11IsL5HyOqSI6CZSoBon+B1cCIQ8Tckd44zTbhHjqLBQPiykzhmzXwqv5NFlcroDCZ2rVN9ucrZqi39CGy2zkMYi2JaGjc=");
//        baseSelfReq.setAesData("xxufLD6FS95fqoLpIvxWw9Mj433DwY4V3uQteLw0m1q4J0h9UQFtFylAf3yCvUBQjinq7C8WM9xYx5ovMa6/8scICwCSBAaBZz4njJbsLcooI1vzc/W8rsjPv/rowJ3VaC+PlgYikGS5UM7Rp79y9MyYoxQbyCDs9MEuLzDsPQtCK2LegozerPyhXW9CRrs5");
//        decryptData(baseSelfReq, privateKey);
//        try {
//            String content = AesUtil.getKey();
//            System.out.println("原始字符串是：" + content);
//            // 获得密钥对
//            KeyPair keyPair = RSAUtil.getKeyPair();
//            // 获得进行Base64 加密后的公钥和私钥 String
//
//            String privateKeyStr = RSAUtil.getPrivateKey(keyPair);
//            String publicKeyStr = RSAUtil.getPublicKey(keyPair);
//            System.out.println("Base64处理后的私钥：" + privateKeyStr + "\n"
//                    + "Base64处理后的公钥：" + publicKeyStr);
//
//            // 获得原始的公钥和私钥，并以字符串形式打印出来
//            PrivateKey privateKey = RSAUtil.string2Privatekey(privateKeyStr);
//            PublicKey publicKey = RSAUtil.string2PublicKey(publicKeyStr);
//
//            // 公钥加密/私钥解密
//            byte[] publicEncryBytes = RSAUtil.publicEncrytype(content.getBytes(), publicKey);
//            System.out.println("公钥加密后长度："+ publicEncryBytes.length);
//            System.out.println("公钥加密后的字符串(经BASE64处理)：" + Base64.getEncoder().encodeToString(publicEncryBytes));
//            System.out.println("公钥加密后长度："+ org.apache.commons.codec.binary.Base64.encodeBase64String(publicEncryBytes).getBytes().length);
//            byte[] privateDecryBytes = RSAUtil.privateDecrypt(publicEncryBytes, privateKey);
//            System.out.println("私钥解密后的原始字符串：" + new String(privateDecryBytes));
//
//            String privateDecryStr = new String(privateDecryBytes, StandardCharsets.UTF_8);
//            if (content.equals(privateDecryStr)) {
//                System.out.println("测试通过！");
//            } else {
//                System.out.println("测试未通过！");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
