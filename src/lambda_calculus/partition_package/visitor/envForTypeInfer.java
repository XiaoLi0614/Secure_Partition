package lambda_calculus.partition_package.visitor;

import java.util.ArrayList;
import java.util.HashMap;

public class envForTypeInfer {
    //public HashMap<String, CIAType> Gamma;
    public String currentHosts;
    public ArrayList<String> currentContextC;
    public ArrayList<String> currentContextI;
    public ArrayList<String> currentContextA;

    public envForTypeInfer(){
        currentContextC = new ArrayList<>();
        currentContextI = new ArrayList<>();
        currentContextA = new ArrayList<>();
        return;
    }

/*    public HashMap<String, CIAType> getGamma(){
        return this.Gamma;
    }

    public void setGamma(HashMap<String, CIAType> g){
        Gamma = new HashMap<>();
        for(HashMap.Entry<String, CIAType> entry : g.entrySet()){
            Gamma.put(entry.getKey(), entry.getValue().clone());
        }
        return;
    }*/

/*    public nodeSet getCurrentHost(){
        return this.currentHosts;
    }

    public void setCurrentHost(nodeSet h){
        currentHosts = h.clone();
        return;
    }*/

/*    public CIAType getCurrentContext(){
        return this.currentContext;
    }

    public void setCurrentContext(CIAType c){
        currentContext = c.clone();
        return;
    }*/

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        envForTypeInfer that = (envForTypeInfer) o;
        if(!currentHosts.equals(that.currentHosts)) return false;
        //else return currentContext.equals(that.currentContext);
        else if (! currentContextC.equals(that.currentContextC)) return false;
        else if (! currentContextI.equals(that.currentContextI)) return false;
        else return currentContextA.equals(that.currentContextA);
    }

    @Override
    public envForTypeInfer clone(){
        envForTypeInfer r = new envForTypeInfer();
        r.currentHosts = this.currentHosts;
        //there is no need for clone method for string because string object is immutable.
        r.currentContextC = this.currentContextC;
        r.currentContextI = this.currentContextI;
        r.currentContextA = this.currentContextA;
        return r;
    }
}
