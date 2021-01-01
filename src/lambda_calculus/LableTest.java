package lambda_calculus;

import lambda_calculus.partition_package.visitor.CIAType;
import lambda_calculus.partition_package.visitor.quorumDef;

import java.util.HashSet;

public class LableTest {
    public static void main(String[] args){
        //test for CIA labels
/*        CIAType l1 = new CIAType();
        HashSet<Integer> c1 = new HashSet<>();
        c1.add(1);
        c1.add(2);
        l1.Confidentiality = c1;
        HashSet<Integer> c2 = new HashSet<>();
        c2.add(1);
        System.out.println(l1.cLeq(c2));

        HashSet<HashSet<Integer>> i1  = new HashSet<>();
        l1.Integrity = i1;
        HashSet<Integer> i1_qs1 = new HashSet<>();
        i1_qs1.add(1);
        i1_qs1.add(2);
        HashSet<Integer> i1_qs_2 = new HashSet<>();
        i1_qs_2.add(2);
        i1_qs_2.add(3);
        i1.add(i1_qs1);
        i1.add(i1_qs_2);
        HashSet<HashSet<Integer>> i2 = new HashSet<>();
        HashSet<Integer> i2_qs1 = new HashSet<>();
        i2.add(i2_qs1);
        i2_qs1.add(1);
        i2_qs1.add(2);
        i2_qs1.add(4);
        System.out.println(l1.iJoin(i2));
        System.out.println(l1.Integrity);
        System.out.println(i2);*/

        //test for quorum and B
        quorumDef QTest = new quorumDef();
        HashSet<HashSet<Integer>> Q = new HashSet<>();
        HashSet<Integer> qs1 = new HashSet<>();
        Q.add(qs1);
        qs1.add(2);
        qs1.add(3);
        qs1.add(4);
        HashSet<Integer> qs2 = new HashSet<>();
        Q.add(qs2);
        qs2.add(1);
        qs2.add(3);
        qs2.add(4);
        HashSet<Integer> qs3 = new HashSet<>();
        Q.add(qs3);
        qs3.add(1);
        qs3.add(2);
        qs3.add(4);
        HashSet<Integer> qs4 = new HashSet<>();
        Q.add(qs4);
        qs4.add(1);
        qs4.add(2);
        qs4.add(3);
        //HashSet<Integer> qs5 = new HashSet<>();
        //Q.add(qs5);
        //qs5.add(1);
        QTest.quorum = Q;
        HashSet<HashSet<Integer>> B =  new HashSet<>();
        HashSet<Integer> bqs1 = new HashSet<>();
        B.add(bqs1);
        bqs1.add(1);
        bqs1.add(5);
        HashSet<Integer> bqs2 = new HashSet<>();
        B.add(bqs2);
        bqs2.add(2);
        bqs2.add(6);
        HashSet<Integer> bqs3 = new HashSet<>();
        B.add(bqs3);
        bqs3.add(3);
        HashSet<Integer> bqs4 = new HashSet<>();
        B.add(bqs4);
        bqs4.add(4);
        HashSet<Integer> H = new HashSet<>();
        H.add(1);
        H.add(2);
        H.add(3);
        H.add(4);
        //System.out.println(QTest.availabilityCons(B));
        //System.out.println(QTest.fieldIntegrity(B));
        //System.out.println(QTest.methodIntegrity(B));
        System.out.println(QTest.availabilityProj(B, H));


        return;
        //HashSet<Integer> i2_qs2 = new HashSet<>();
    }
}
