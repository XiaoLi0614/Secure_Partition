package lambda_calculus.partition_package.visitor;

import fj.Hash;
import graph.lang.ast.False;
import lesani.compiler.ast.HolderNode;

import java.util.HashSet;

public class CIAType implements Cloneable{

/*    //for integrity and availability we have to implement deep copy by our own
    public HashSet<Integer> Confidentiality;
    public HashSet<HashSet<Integer>> Integrity;
    public HashSet<HashSet<Integer>> Availability;

    public CIAType(){
        this.Confidentiality = new HashSet<Integer>();
        this.Integrity = new HashSet<>();
        this.Availability = new HashSet<>();
        return;
    }

    public CIAType(HashSet<Integer> c, HashSet<HashSet<Integer>> i, HashSet<HashSet<Integer>> a){
        this.Confidentiality = (HashSet<Integer>) c.clone();
        this.Integrity = new HashSet<>();
        for(HashSet<Integer> iSet : i){
            this.Integrity.add((HashSet<Integer>) iSet.clone());
        }
        this.Availability = new HashSet<>();
        for(HashSet<Integer> aSet : a){
            this.Availability.add((HashSet<Integer>) aSet.clone());
        }
        return;
    }

    public HashSet<Integer> getConfidentiality(){
        return this.Confidentiality;
    }

    public void setConfidentiality(HashSet<Integer> c){
        this.Confidentiality = (HashSet<Integer>) c.clone();
        return;
    }

    public HashSet<HashSet<Integer>> getIntegrity(){
        return this.Integrity;
    }

    public void setIntegrity(HashSet<HashSet<Integer>> i){
        this.Integrity = new HashSet<>();
        for(HashSet<Integer> qs : i){
            this.Integrity.add((HashSet<Integer>) qs.clone());
        }
        return;
    }

    public HashSet<HashSet<Integer>> getAvailability(){
        return this.Availability;
    }

    public void setAvailability(HashSet<HashSet<Integer>> a){
        this.Availability = new HashSet<>();
        for(HashSet<Integer> qs : a){
            this.Availability.add((HashSet<Integer>) qs.clone());
        }
        return;
    }


    //c1 join c2 = c1 intersection c2
    public HashSet<Integer> cJoin(HashSet<Integer>c2){
        HashSet<Integer> retC = new HashSet<>((HashSet<Integer>)this.Confidentiality.clone());
        retC.retainAll(c2);
        return retC;
    }

    //c1 meet c2 = c1 union c2
    public HashSet<Integer> cMeet(HashSet<Integer>c2){
        HashSet<Integer> retC = new HashSet<>((HashSet<Integer>)this.Confidentiality.clone());
        retC.addAll(c2);
        return retC;
    }

    // c1 <= c2 : c1 is the superset of c2
    public Boolean cLeq(HashSet<Integer> c2){
        return this.Confidentiality.containsAll(c2);
    }

    //i1 join i2 = {ib1 union ib2 | ib1 \in i1, ib2 \in i2}
    public HashSet<HashSet<Integer>> iJoin(HashSet<HashSet<Integer>> i2){
        HashSet<HashSet<Integer>> retI = new HashSet<>();
        for(HashSet<Integer> ib1: this.Integrity){
            for(HashSet<Integer> ib2: i2){
                HashSet<Integer> tempSet = (HashSet<Integer>) ib1.clone();
                tempSet.addAll(ib2);
                retI.add(tempSet);
            }
        }
        return retI;
    }

    //i1 meet i2 = {ib1 intersection ib2 | ib1 \in i1, ib2 \in i2}
    public HashSet<HashSet<Integer>> iMeet(HashSet<HashSet<Integer>> i2){
        HashSet<HashSet<Integer>> retI = new HashSet<>();
        for(HashSet<Integer> ib1: this.Integrity){
            for(HashSet<Integer> ib2: i2){
                HashSet<Integer> tempSet = (HashSet<Integer>) ib1.clone();
                tempSet.retainAll(ib2);
                retI.add(tempSet);
            }
        }
        return retI;
    }

    //i1 <= i2: for all ib1 \in i1, there exists ib2 \in i2 such that ib2 is the superset of ib1
    public Boolean iLeq(HashSet<HashSet<Integer>> i2){
        for(HashSet<Integer> ib1 : this.Integrity){
            Boolean findSupSet = false;
            for(HashSet<Integer> ib2: i2){
                if(ib2.containsAll(ib1)){
                    findSupSet = true;
                    break;
                }
            }
            if(!findSupSet){
                return false;
            }
        }
        return true;
    }

    //a1 join a2 = {ab1 union ab2 | ab1 \in a1, ab2 \in a2}
    public HashSet<HashSet<Integer>> aJoin(HashSet<HashSet<Integer>> a2){
        HashSet<HashSet<Integer>> retI = new HashSet<>();
        for(HashSet<Integer> ab1: this.Integrity){
            for(HashSet<Integer> ab2: a2){
                HashSet<Integer> tempSet = (HashSet<Integer>) ab1.clone();
                tempSet.addAll(ab2);
                retI.add(tempSet);
            }
        }
        return retI;
    }

    //a1 meet a2 = {ab1 intersection ab2 | ab1 \in a1, ab2 \in a2}
    public HashSet<HashSet<Integer>> aMeet(HashSet<HashSet<Integer>> a2){
        HashSet<HashSet<Integer>> retI = new HashSet<>();
        for(HashSet<Integer> ab1: this.Integrity){
            for(HashSet<Integer> ab2: a2){
                HashSet<Integer> tempSet = (HashSet<Integer>) ab1.clone();
                tempSet.retainAll(ab2);
                retI.add(tempSet);
            }
        }
        return retI;
    }

    //a1 <= a2: for all ab1 \in a1, there exists ab2 \in a2 such that ab2 is the superset of ab1
    public Boolean aLeq(HashSet<HashSet<Integer>> a2){
        for(HashSet<Integer> ab1 : this.Availability){
            Boolean findSupSet = false;
            for(HashSet<Integer> ab2: a2){
                if(ab2.containsAll(ab1)){
                    findSupSet = true;
                    break;
                }
            }
            if(!findSupSet){
                return false;
            }
        }
        return true;
    }

    public CIAType ciaJoin(CIAType cia){
        CIAType retCIA = new CIAType();
        retCIA.Confidentiality = this.cJoin(cia.Confidentiality);
        retCIA.Integrity = this.iMeet(cia.Integrity);
        retCIA.Availability = this.aMeet(cia.Availability);
        return retCIA;
    }

    public CIAType ciaMeet(CIAType cia){
        CIAType retCIA = new CIAType();
        retCIA.Confidentiality = this.cMeet(cia.Confidentiality);
        retCIA.Integrity = this.iJoin(cia.Integrity);
        retCIA.Availability = this.aJoin(cia.Availability);
        return retCIA;
    }

    public Boolean ciaLeq(CIAType cia){
        if(this.cLeq(cia.Confidentiality) && cia.iLeq(this.Integrity) && cia.aLeq(this.Availability))
            return true;
        else
            return false;
    }

    @Override
    public CIAType clone(){
        CIAType returnLable = new CIAType();
        returnLable.setConfidentiality(this.Confidentiality);
        returnLable.setIntegrity(this.Integrity);
        returnLable.setAvailability(this.Availability);
        return returnLable;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CIAType that = (CIAType) o;
        if(!Confidentiality.equals(that.Confidentiality)) return false;
        else if ()
        //if(!condition.equals(that.condition)) return false;
        //else if (! command1.equals(that.command1)) return false;
        //else return command2.equals(that.command2);
    }*/

    //for integrity and availability we have to implement deep copy by our own
    public nodeSet Confidentiality;
    public quorumDef Integrity;
    public quorumDef Availability;

    public CIAType(){
        this.Confidentiality = new nodeSet();
        this.Integrity = new quorumDef();
        this.Availability = new quorumDef();
        return;
    }

    public CIAType(nodeSet c, quorumDef i, quorumDef a){
        this.Confidentiality = c.clone();
        this.Integrity = i.clone();
        this.Availability = a.clone();
        return;
    }

    public nodeSet getConfidentiality(){
        return this.Confidentiality;
    }

    public void setConfidentiality(nodeSet c){
        this.Confidentiality = c.clone();
        return;
    }

    public quorumDef getIntegrity(){
        return this.Integrity;
    }

    public void setIntegrity(quorumDef i){
        this.Integrity = i.clone();
        return;
    }

    public quorumDef getAvailability(){
        return this.Availability;
    }

    public void setAvailability(quorumDef a){
        this.Availability = a.clone();
        return;
    }


    //c1 join c2 = c1 intersection c2
    public nodeSet cJoin(nodeSet c2){
        nodeSet retC = this.Confidentiality.clone();
        retC.nSet.retainAll(c2.nSet);
        return retC;
    }

    //c1 meet c2 = c1 union c2
    public nodeSet cMeet(nodeSet c2){
        nodeSet retC = this.Confidentiality.clone();
        retC.nSet.addAll(c2.nSet);
        return retC;
    }

    // c1 <= c2 : c1 is the superset of c2
    public Boolean cLeq(nodeSet c2){
        return this.Confidentiality.nSet.containsAll(c2.nSet);
    }

    //i1 join i2 = {ib1 union ib2 | ib1 \in i1, ib2 \in i2}
    public quorumDef iJoin(quorumDef i2){
        quorumDef retI = new quorumDef();
        for(nodeSet ib1: this.Integrity.quorum){
            for(nodeSet ib2: i2.quorum){
                nodeSet tempSet = ib1.clone();
                tempSet.nSet.addAll(ib2.nSet);
                retI.quorum.add(tempSet);
            }
        }
        return retI;
    }

    //i1 meet i2 = {ib1 intersection ib2 | ib1 \in i1, ib2 \in i2}
    public quorumDef iMeet(quorumDef i2){
        quorumDef retI = new quorumDef();
        for(nodeSet ib1: this.Integrity.quorum){
            for(nodeSet ib2: i2.quorum){
                nodeSet tempSet = ib1.clone();
                tempSet.nSet.retainAll(ib2.nSet);
                retI.quorum.add(tempSet);
            }
        }
        return retI;
    }

    //i1 <= i2: for all ib1 \in i1, there exists ib2 \in i2 such that ib2 is the superset of ib1
    public Boolean iLeq(quorumDef i2){
        for(nodeSet ib1 : this.Integrity.quorum){
            Boolean findSupSet = false;
            for(nodeSet ib2: i2.quorum){
                if(ib2.nSet.containsAll(ib1.nSet)){
                    findSupSet = true;
                    break;
                }
            }
            if(!findSupSet){
                return false;
            }
        }
        return true;
    }

    //a1 join a2 = {ab1 union ab2 | ab1 \in a1, ab2 \in a2}
    public quorumDef aJoin(quorumDef a2){
        quorumDef retI = new quorumDef();
        for(nodeSet ab1: this.Integrity.quorum){
            for(nodeSet ab2: a2.quorum){
                nodeSet tempSet = ab1.clone();
                tempSet.nSet.addAll(ab2.nSet);
                retI.quorum.add(tempSet);
            }
        }
        return retI;
    }

    //a1 meet a2 = {ab1 intersection ab2 | ab1 \in a1, ab2 \in a2}
    public quorumDef aMeet(quorumDef a2){
        quorumDef retI = new quorumDef();
        for(nodeSet ab1: this.Integrity.quorum){
            for(nodeSet ab2: a2.quorum){
                nodeSet tempSet = ab1.clone();
                tempSet.nSet.retainAll(ab2.nSet);
                retI.quorum.add(tempSet);
            }
        }
        return retI;
    }

    //a1 <= a2: for all ab1 \in a1, there exists ab2 \in a2 such that ab2 is the superset of ab1
    public Boolean aLeq(quorumDef a2){
        for(nodeSet ab1 : this.Availability.quorum){
            Boolean findSupSet = false;
            for(nodeSet ab2: a2.quorum){
                if(ab2.nSet.containsAll(ab1.nSet)){
                    findSupSet = true;
                    break;
                }
            }
            if(!findSupSet){
                return false;
            }
        }
        return true;
    }

    public CIAType ciaJoin(CIAType cia){
        CIAType retCIA = new CIAType();
        retCIA.Confidentiality = this.cJoin(cia.Confidentiality);
        retCIA.Integrity = this.iMeet(cia.Integrity);
        retCIA.Availability = this.aMeet(cia.Availability);
        return retCIA;
    }

    public CIAType ciaMeet(CIAType cia){
        CIAType retCIA = new CIAType();
        retCIA.Confidentiality = this.cMeet(cia.Confidentiality);
        retCIA.Integrity = this.iJoin(cia.Integrity);
        retCIA.Availability = this.aJoin(cia.Availability);
        return retCIA;
    }

    public Boolean ciaLeq(CIAType cia){
        if(this.cLeq(cia.Confidentiality) && cia.iLeq(this.Integrity) && cia.aLeq(this.Availability))
            return true;
        else
            return false;
    }

    @Override
    public CIAType clone(){
        CIAType returnLable = new CIAType();
        returnLable.setConfidentiality(this.Confidentiality);
        returnLable.setIntegrity(this.Integrity);
        returnLable.setAvailability(this.Availability);
        return returnLable;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CIAType that = (CIAType) o;
        if(!Confidentiality.equals(that.Confidentiality)) return false;
        else if(!Integrity.equals(that.Integrity)) return false;
        else return Availability.equals(that.Availability);
    }

    @Override
    public String toString(){
        return "C:" + Confidentiality + " I: " + Integrity + " A: " + Availability;
    }

    @Override
    public  int hashCode(){
        int r = Confidentiality.hashCode() + Integrity.hashCode() + Availability.hashCode();
        return r;
    }
}
