package lambda_calculus.partition_package.visitor;

import lesani.collection.Pair;

import java.util.HashMap;
import java.util.HashSet;

public class envForTypeCheck {
    public HashMap<String, CIAType> Gamma;
    public HashMap<String, Pair<quorumDef, quorumDef>> OMap;
    public HashMap<String, Pair<nodeSet, quorumDef>> MMap;
    public nodeSet currentHosts;
    public CIAType currentContext;

    public envForTypeCheck(){
        Gamma = new HashMap<>();
        OMap = new HashMap<>();
        MMap = new HashMap<>();
        currentHosts = new nodeSet();
        currentContext = new CIAType();
        return;
    }

    public HashMap<String, CIAType> getGamma(){
        return this.Gamma;
    }

    public void setGamma(HashMap<String, CIAType> g){
        Gamma = new HashMap<>();
        for(HashMap.Entry<String, CIAType> entry : g.entrySet()){
            Gamma.put(entry.getKey(), entry.getValue().clone());
        }
        return;
    }

    public HashMap<String, Pair<quorumDef, quorumDef>> getOMap(){
        return this.OMap;
    }

    public void setOMap(HashMap<String, Pair<quorumDef, quorumDef>> o){
        OMap = new HashMap<>();
        for(HashMap.Entry<String, Pair<quorumDef, quorumDef>> entry : o.entrySet()){
            Pair<quorumDef, quorumDef> temp = new Pair<>(entry.getValue().element1.clone(), entry.getValue().element2.clone());
            OMap.put(entry.getKey(), temp);
        }
        return;
    }

    public HashMap<String, Pair<nodeSet, quorumDef>> getMMap(){
        return this.MMap;
    }

    public void setMMap(HashMap<String, Pair<nodeSet, quorumDef>> m){
        MMap = new HashMap<>();
        for(HashMap.Entry<String, Pair<nodeSet, quorumDef>> entry : m.entrySet()){
            Pair<nodeSet, quorumDef> temp = new Pair<>(entry.getValue().element1.clone(), entry.getValue().element2.clone());
            MMap.put(entry.getKey(), temp);
        }
        return;
    }

    public nodeSet getCurrentHost(){
        return this.currentHosts;
    }

    public void setCurrentHost(nodeSet h){
        currentHosts = h.clone();
        return;
    }

    public CIAType getCurrentContext(){
        return this.currentContext;
    }

    public void setCurrentContext(CIAType c){
        currentContext = c.clone();
        return;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        envForTypeCheck that = (envForTypeCheck) o;
        if(!Gamma.equals(that.Gamma)) return false;
        else if(!OMap.equals(that.OMap)) return false;
        else if(!MMap.equals(that.MMap)) return false;
        else if(!currentHosts.equals(that.currentHosts)) return false;
        else return currentContext.equals(that.currentContext);
    }

    @Override
    public envForTypeCheck clone(){
        envForTypeCheck r = new envForTypeCheck();
        r.setGamma(this.Gamma);
        r.setOMap(this.OMap);
        r.setMMap(this.MMap);
        r.setCurrentHost(this.currentHosts);
        r.setCurrentContext(this.currentContext);
        return r;
    }
}
