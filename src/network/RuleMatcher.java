package network;

import signature.SignatureParser;

import java.util.ArrayList;
import java.util.List;

public final class RuleMatcher {
    private final SignatureParser parser;
    private final NetworkTraffic networkTraffic;
    private final List<Packet> matched_traffic;

    public RuleMatcher(SignatureParser parser, NetworkTraffic networkTraffic) {
        this.parser = parser;
        this.networkTraffic = networkTraffic;
        this.matched_traffic = new ArrayList<>();
    }

    public void match(){
        Long inPort;
        Long outPort;

        if (parser.getInLowerBound() != null && parser.getInUpperBound() == null){
            if(parser.getOutLowerBound() != null && parser.getOutUpperBound() == null){
                for(Packet var: networkTraffic.getPackets()){
                    if(var.getAttrib().get("Src Port") == null || var.getAttrib().get("Dst Port") == null){
                        continue;
                    }
                    inPort = Long.parseLong(var.getAttrib().get("Src Port"));
                    outPort = Long.parseLong(var.getAttrib().get("Dst Port"));

                    if(inPort >= parser.getInLowerBound() && outPort >= parser.getOutLowerBound()){
                        matched_traffic.add(var);
                    }
                }
            }
            else if (parser.getOutLowerBound() == null && parser.getOutUpperBound() != null){
                for(Packet var: networkTraffic.getPackets()){
                    if(var.getAttrib().get("Src Port") == null || var.getAttrib().get("Dst Port") == null){
                        continue;
                    }
                    inPort = Long.parseLong(var.getAttrib().get("Src Port"));
                    outPort = Long.parseLong(var.getAttrib().get("Dst Port"));
                    if(inPort >= parser.getInLowerBound() && outPort <= parser.getOutUpperBound()){
                        matched_traffic.add(var);
                    }
                }
            }
            else {
                for(Packet var: networkTraffic.getPackets()){
                    if(var.getAttrib().get("Src Port") == null || var.getAttrib().get("Dst Port") == null){
                        continue;
                    }
                    inPort = Long.parseLong(var.getAttrib().get("Src Port"));
                    outPort = Long.parseLong(var.getAttrib().get("Dst Port"));
                    if(inPort >= parser.getInLowerBound() &&
                            outPort >= parser.getOutLowerBound() &&
                            outPort <= parser.getOutUpperBound()){
                        matched_traffic.add(var);
                    }

                }
            }
        }
        else if(parser.getInLowerBound() == null && parser.getInUpperBound() != null){
            if(parser.getOutLowerBound() != null && parser.getOutUpperBound() == null){
                for(Packet var: networkTraffic.getPackets()){
                    if(var.getAttrib().get("Src Port") == null || var.getAttrib().get("Dst Port") == null){
                        continue;
                    }
                    inPort = Long.parseLong(var.getAttrib().get("Src Port"));
                    outPort = Long.parseLong(var.getAttrib().get("Dst Port"));
                    if(inPort <= parser.getInUpperBound() && outPort >= parser.getOutLowerBound()){
                        matched_traffic.add(var);
                    }
                }
            }
            else if (parser.getOutLowerBound() == null && parser.getOutUpperBound() != null){
                for(Packet var: networkTraffic.getPackets()){
                    if(var.getAttrib().get("Src Port") == null || var.getAttrib().get("Dst Port") == null){
                        continue;
                    }
                    inPort = Long.parseLong(var.getAttrib().get("Src Port"));
                    outPort = Long.parseLong(var.getAttrib().get("Dst Port"));
                    if(inPort <= parser.getInUpperBound() && outPort <= parser.getOutUpperBound()){
                        matched_traffic.add(var);
                    }
                }
            }
            else {
                for(Packet var: networkTraffic.getPackets()){
                    if(var.getAttrib().get("Src Port") == null || var.getAttrib().get("Dst Port") == null){
                        continue;
                    }
                    inPort = Long.parseLong(var.getAttrib().get("Src Port"));
                    outPort = Long.parseLong(var.getAttrib().get("Dst Port"));
                    if(inPort <= parser.getInUpperBound() &&
                            outPort <= parser.getOutUpperBound() &&
                            outPort >= parser.getOutLowerBound()){
                        matched_traffic.add(var);
                    }
                }
            }
        }
        else{
            if(parser.getOutLowerBound() != null && parser.getOutUpperBound() == null){
                for(Packet var: networkTraffic.getPackets()){
                    if(var.getAttrib().get("Src Port") == null || var.getAttrib().get("Dst Port") == null){
                        continue;
                    }
                    inPort = Long.parseLong(var.getAttrib().get("Src Port"));
                    outPort = Long.parseLong(var.getAttrib().get("Dst Port"));
                    if(inPort <= parser.getInUpperBound() &&
                            inPort >= parser.getInLowerBound() &&
                            outPort >= parser.getOutLowerBound()){
                        matched_traffic.add(var);
                    }
                }
            }
            else if (parser.getOutLowerBound() == null && parser.getOutUpperBound() != null){
                for(Packet var: networkTraffic.getPackets()){
                    if(var.getAttrib().get("Src Port") == null || var.getAttrib().get("Dst Port") == null){
                        continue;
                    }
                    inPort = Long.parseLong(var.getAttrib().get("Src Port"));
                    outPort = Long.parseLong(var.getAttrib().get("Dst Port"));
                    if(inPort <= parser.getInUpperBound() &&
                            inPort >= parser.getInLowerBound() &&
                            outPort <= parser.getOutUpperBound()){
                        matched_traffic.add(var);
                    }
                }
            }
            else {
                for(Packet var: networkTraffic.getPackets()){
                    if(var.getAttrib().get("Src Port") == null || var.getAttrib().get("Dst Port") == null){
                        continue;
                    }
                    inPort = Long.parseLong(var.getAttrib().get("Src Port"));
                    outPort = Long.parseLong(var.getAttrib().get("Dst Port"));
                    if(inPort <= parser.getInUpperBound() && outPort <= parser.getOutUpperBound() &&
                            inPort >= parser.getInLowerBound() && outPort >= parser.getOutLowerBound()){
                        matched_traffic.add(var);
                    }
                }
            }
        }
    }

    public List<Packet> getMatched_traffic() {
        return matched_traffic;
    }
}
