package lambda_calculus.partition_package.visitor;

import lesani.collection.Pair;

import java.util.HashMap;
import java.util.HashSet;

public class envForTypeCheck {
    public HashMap<String, CIAType> Gamma;
    public HashMap<String, Pair<quorumDef, quorumDef>> OMap;
    public HashMap<String, Pair<HashSet<Integer>, quorumDef>> MMap;
    public HashSet<Integer> currentHosts;
    public CIAType currentContext;

    public envForTypeCheck(){
        Gamma = new HashMap<>();
        OMap = new HashMap<>();
        MMap = new HashMap<>();
        currentHosts = new HashSet<>();
        currentContext = new CIAType();
        return;
    }

    public HashMap<String, CIAType> getGamma(){
        return  this.Gamma;
    }

    public void setGamma(HashMap<String, CIAType>)
}
