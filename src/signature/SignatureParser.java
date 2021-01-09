package signature;

import encoder.EncodingScheme;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class SignatureParser {
    private Long inLowerBound, inUpperBound, outLowerBound,
            outUpperBound, Encoded_IP_IN, Encoded_IP_OUT, Encoded_Protocol;
    public SignatureParser(String file, EncodingScheme encoder) throws IOException {
        parser(file, encoder);
    }

    private void parser(String file, EncodingScheme encoder) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while (line != null){
            String[] inbound_outbound = line.split("->");
            String inbound = inbound_outbound[0];
            String outbound = inbound_outbound[1];
            String[] inbound_attrib = inbound.split(" ");
            String protocol = inbound_attrib[0];
            this.Encoded_Protocol = encoder.getEncodedValue(protocol);
            String IN_IP = inbound_attrib[1];
            this.Encoded_IP_IN = encoder.getEncodedValue(IN_IP);
            String inbound_port = inbound_attrib[2];
            String[] outbound_attrib = outbound.split(" ");
            String OUT_IP = outbound_attrib[1];
            this.Encoded_IP_OUT = encoder.getEncodedValue(OUT_IP);
            String outbound_port = outbound_attrib[2];
            String[] lower_upper;
            if (inbound_port.matches("\\d+:\\d+")){
                lower_upper = inbound_port.split(":");
                this.inLowerBound = Long.parseLong(lower_upper[0]);
                this.inUpperBound = Long.parseLong(lower_upper[1]);
            }
            else if (inbound_port.matches(":\\d+")){
                lower_upper = inbound_port.split(":");
                this.inLowerBound = null;
                this.inUpperBound = Long.parseLong(lower_upper[1]);
            }
            else{
                lower_upper = inbound_port.split(":");
                this.inLowerBound = Long.parseLong(lower_upper[0]);
                this.inUpperBound = null;
            }
            if (outbound_port.matches("\\d+:\\d+")){
                lower_upper = outbound_port.split(":");
                this.outLowerBound = Long.parseLong(lower_upper[0]);
                this.outUpperBound = Long.parseLong(lower_upper[1]);
            }
            else if (outbound_port.matches(":\\d+")){
                lower_upper = outbound_port.split(":");
                this.outLowerBound = null;
                this.outUpperBound = Long.parseLong(lower_upper[1]);
            }
            else{
                lower_upper = outbound_port.split(":");
                this.outLowerBound = Long.parseLong(lower_upper[0]);
                this.outUpperBound = null;
            }
            line = reader.readLine();
        }
    }

    public Long getInLowerBound() {
        return inLowerBound;
    }

    public Long getInUpperBound() {
        return inUpperBound;
    }

    public Long getOutLowerBound() {
        return outLowerBound;
    }

    public Long getOutUpperBound() {
        return outUpperBound;
    }

    public Long getEncoded_IP_IN() {
        return Encoded_IP_IN;
    }

    public Long getEncoded_IP_OUT() {
        return Encoded_IP_OUT;
    }

    public Long getEncoded_Protocol() {
        return Encoded_Protocol;
    }
}
