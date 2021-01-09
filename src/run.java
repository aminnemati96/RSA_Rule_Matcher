import encoder.EncodingScheme;
import network.NetworkTraffic;
import network.Packet;
import network.RuleMatcher;
import signature.SignatureOperations;
import signature.SignatureParser;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;

public class run {
    public static void main(String[] args)
            throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IOException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();
        SignatureOperations.encryption(kp.getPublic(),
                "/Users/aminnemati/IdeaProjects/Conventional/Files/Signature.txt",
                "/Users/aminnemati/IdeaProjects/Conventional/Files/Enc.txt");
        Instant start = Instant.now();
        SignatureOperations.decryption(kp.getPrivate(),
                "/Users/aminnemati/IdeaProjects/Conventional/Files/Enc.txt",
                "/Users/aminnemati/IdeaProjects/Conventional/Files/results.txt");
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        EncodingScheme encodingScheme = new EncodingScheme
                ("/Users/aminnemati/IdeaProjects/Conventional/Files/Encoding_Scheme.txt");
        NetworkTraffic networkTraffic = new NetworkTraffic
                ("/Users/aminnemati/IdeaProjects/Conventional/Files/small.txt");
        start = Instant.now();
        SignatureParser parser = new SignatureParser
                ("/Users/aminnemati/IdeaProjects/Conventional/Files/results.txt", encodingScheme);
        RuleMatcher ruleMatcher = new RuleMatcher(parser, networkTraffic);
        ruleMatcher.match();
        finish = Instant.now();
        long timeElapsed2 = Duration.between(start, finish).toMillis();
        for(Packet var: ruleMatcher.getMatched_traffic())
        {
            System.out.println(var.getContent());
        }
        System.out.println("Time required for decryption: " + timeElapsed);
        System.out.println("Time required for performing matching: " + timeElapsed2);
        System.out.println("Total Elapsed time :" + (timeElapsed + timeElapsed2));
        System.out.println("Total number of matched packets: " + ruleMatcher.getMatched_traffic().size());
    }
}
