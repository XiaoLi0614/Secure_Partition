package lambda_calculus.partition_package.visitor;

import java.util.HashSet;

public class nodeSet implements Cloneable{
    public HashSet<Integer> nSet;

    public nodeSet(){
        nSet = new HashSet<>();
        return;
    }

    public nodeSet(HashSet<Integer> n){
        nSet = (HashSet<Integer>) n.clone();
        return;
    }

    @Override
    public int hashCode(){
        int r = 0;
        for(Integer i : nSet){
            r += i.intValue();
        }
        return r;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        nodeSet that = (nodeSet) o;
        for(Integer i1 : that.nSet){
            //note that from -127 to 127 is cached to have the same address if the int value is the same
            //TODO: in our application the nodes number does not exceed 128. But we need to change this part for scalability
            if(!this.nSet.contains(i1)) return false;
        }
        for(Integer i2 : this.nSet){
            if(!that.nSet.contains(i2)) return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return nSet.toString();
    }

    @Override
    public nodeSet clone(){
        nodeSet r = new nodeSet();
        r.nSet = (HashSet<Integer>) this.nSet.clone();
        return r;
    }
}
