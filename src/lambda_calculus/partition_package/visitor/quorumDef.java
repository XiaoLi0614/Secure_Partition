package lambda_calculus.partition_package.visitor;

import java.util.HashSet;
import java.util.Queue;

public class quorumDef {
    public HashSet<HashSet<Integer>> quorum;

    public quorumDef(){
        this.quorum = new HashSet<>();
        return;
    }

    //deep copy for quorum slices
    public quorumDef(HashSet<HashSet<Integer>> q){
        this.quorum = new HashSet<>();
        for(HashSet<Integer> qSet : q){
            this.quorum.add((HashSet<Integer>) qSet.clone());
        }
        return;
    }

    //for all qs in quorum and for all qsb in B. q \ b != \emptyset
    public Boolean methodIntegrity(HashSet<HashSet<Integer>> B){
        for(HashSet<Integer> qs : this.quorum){
            for(HashSet<Integer> bqs : B){
                if(bqs.containsAll(qs))
                    return false;
            }
        }
        return true;
    }

    //for all qs_1, qs_2 in quorum and qsb in B. qs_1 intersection qs_2 is the superset of qsb
    public Boolean fieldIntegrity(HashSet<HashSet<Integer>> B){
        for(HashSet<Integer> qs1 : this.quorum){
            for(HashSet<Integer> qs2 : this.quorum){
                HashSet tempSet = (HashSet<Integer>) qs1.clone();
                if(!qs1.equals(qs2))
                    tempSet.retainAll(qs2);
                for(HashSet<Integer> qsb : B){
                    if(qsb.containsAll(tempSet))
                        return false;
                }
            }
        }
        return true;
    }

    //for all qsb in B, there exists qs in quorum, such that q is the subset of H / b
    public Boolean availabilityProj(HashSet<HashSet<Integer>> B, HashSet<Integer> H){
        for(HashSet<Integer> qsb : B){
            Boolean findQS = false;
            HashSet<Integer> remainSet = (HashSet<Integer>) H.clone();
            remainSet.removeAll(qsb);
            for(HashSet<Integer> qs : this.quorum){
                if(remainSet.containsAll(qs)){
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
    public Boolean availabilityCons(HashSet<HashSet<Integer>> B){
        for(HashSet<Integer> bqs : B){
            Boolean findSet = false;
            for(HashSet<Integer> qs : this.quorum){
                HashSet<Integer> intersectionSet = (HashSet<Integer>) qs.clone();
                intersectionSet.retainAll(bqs);
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
}
