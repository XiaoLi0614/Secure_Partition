package lambda_calculus.partition_package.visitor;

import java.util.HashSet;
import java.util.Queue;

public class quorumDef {
    public HashSet<nodeSet> quorum;

    public quorumDef(){
        this.quorum = new HashSet<>();
        return;
    }

    //deep copy for quorum slices
    public quorumDef(HashSet<nodeSet> q){
        this.quorum = new HashSet<>();
        for(nodeSet qSet : q){
            this.quorum.add(qSet.clone());
        }
        return;
    }

    public HashSet<nodeSet> getQuorum(){
        return this.quorum;
    }

    public void setQuorum(HashSet<nodeSet> q){
        this.quorum = new HashSet<>();
        for(nodeSet qSet : q){
            this.quorum.add(qSet.clone());
        }
        return;
    }

    //for all qs in quorum and for all qsb in B. q \ b != \emptyset
    public Boolean methodIntegrity(HashSet<nodeSet> B){
        for(nodeSet qs : this.quorum){
            for(nodeSet bqs : B){
                if(bqs.nSet.containsAll(qs.nSet))
                    return false;
            }
        }
        return true;
    }

    //for all qs_1, qs_2 in quorum and qsb in B. qs_1 intersection qs_2 is the superset of qsb
    public Boolean fieldIntegrity(HashSet<nodeSet> B){
        for(nodeSet qs1 : this.quorum){
            for(nodeSet qs2 : this.quorum){
                HashSet<Integer> tempSet = qs1.clone().nSet;
                if(!qs1.equals(qs2))
                    tempSet.retainAll(qs2.nSet);
                for(nodeSet qsb : B){
                    if(qsb.nSet.containsAll(tempSet))
                        return false;
                }
            }
        }
        return true;
    }

    //for all qsb in B, there exists qs in quorum, such that q is the subset of H / b
    public Boolean availabilityProj(HashSet<nodeSet> B, nodeSet H){
        for(nodeSet qsb : B){
            Boolean findQS = false;
            HashSet<Integer> remainSet = H.clone().nSet;
            remainSet.removeAll(qsb.nSet);
            for(nodeSet qs : this.quorum){
                if(remainSet.containsAll(qs.nSet)){
                    findQS = true;
                    break;
                }
            }
            if(!findQS)
                return false;
        }
        return true;
    }

    //for all b, there exist q in Q such that b intersection q =  emptyset
    public Boolean availabilityCons(HashSet<nodeSet> B){
        for(nodeSet bqs : B){
            Boolean findSet = false;
            for(nodeSet qs : this.quorum){
                HashSet<Integer> intersectionSet = qs.clone().nSet;
                intersectionSet.retainAll(bqs.nSet);
                if(intersectionSet.isEmpty()) {
                    findSet = true;
                    break;
                }
            }
            if(!findSet){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        return quorum.toString();
    }

    @Override
    public int hashCode(){
        int r = 0;
        for(nodeSet s : this.quorum){
            r += s.hashCode();
        }
        return  r;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        quorumDef that = (quorumDef) o;
        for(nodeSet n1 : that.quorum){
            if(!this.quorum.contains(n1)) return false;
        }
        for(nodeSet n2 : this.quorum){
            if(!that.quorum.contains(n2)) return false;
        }
        return true;
    }

    @Override
    public quorumDef clone(){
        quorumDef r = new quorumDef();
        for(nodeSet n : this.quorum){
            r.quorum.add(n.clone());
        }
        return r;
    }

}
