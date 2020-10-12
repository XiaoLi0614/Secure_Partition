package graph.lang.visitor;

import consistency.UseCaseCVC;
import graph.lang.ast.*;

import java.util.*;

public class BoundInference {

    public static int index = 0;

    public Multiply multExp = null;

    public Set<Var> boundVariabels;

    public BoundInference()
    {
        boundVariabels = new HashSet<>();
    }

    //Object constraints
    public Exp generateConstraints(UseCaseCVC object)
    {
        return visit(object);
    }

    //Object constraints
    private Exp visit(UseCaseCVC obj)
    {
        ArrayList<Exp> methodConstraints = new ArrayList<>();
        for (Fun method : obj.getOperations())
            methodConstraints.add(visit(method));
        return methodConstraints.stream().reduce(And::new).get();
    }

    //Method constraints
    private Exp visit(Fun method)
    {
        /*
            return type is always tuple
         */
        Tuple returnExp = (Tuple) ((Return)method.body).arg;
        Exp retConstraints = True.getInstance();
        for (int i = 0; i < returnExp.exps.length; i += 2)
        {
            Exp exp = returnExp.exps[i];
            Exp bodyConstraints1 = visitDispatch(exp);
            retConstraints = new And(retConstraints, bodyConstraints1);
            //TODO probably needs fix
            retConstraints.bound = new Plus(new IntLiteral(0), exp.bound);
        }
        return new And(retConstraints, new Gte(new IntLiteral(method.bound), retConstraints.bound));
    }

    //Expression constraints
    private Exp visitDispatch(Exp exp)
    {
        return exp.accept(algebraicExpV);
    }

    public static Exp newVar()
    {
        return new Var(String.valueOf(index++));
    }

    class AlgebraicExpV implements Visitor.ExpVisitor<Exp>{

        class ZOpV implements ZOpVisitor<Exp>
        {
            @Override
            public Exp visit(Var var) {
                var.bound = var;
                boundVariabels.add(var);
                return True.getInstance();
            }

            @Override
            public Exp visit(IntLiteral intLiteral) {
                intLiteral.bound = new IntLiteral(0);
                return True.getInstance();
            }

            @Override
            public Exp visit(Inf inf) {
                return null;
            }

            @Override
            public Exp visit(None none) {
                return null;
            }

            @Override
            public Exp visit(True aTrue) {
                aTrue.bound = new IntLiteral(0);
                return True.getInstance();
            }

            @Override
            public Exp visit(False aFalse) {
                aFalse.bound = new IntLiteral(0);
                return True.getInstance();
            }

            @Override
            public Exp visit(Epsilon epsilon) {
                return null;
            }

            @Override
            public Exp visit(SingeltonLiteral singeltonLiteral) {
//                if(!boundVariabels.contains((Var)singeltonLiteral.i))
                boundVariabels.add((Var)singeltonLiteral.i);
                singeltonLiteral.bound = new IntLiteral(0);
                return True.getInstance();
            }
        }
        ZOpV zOpV = new ZOpV();
        @Override
        public Exp visit(ZOp zOp) {
            return zOp.accept(zOpV);
        }


        class UOpV implements UOpVisitor<Exp>
        {
            @Override
            public Exp visit(Some some) {
                return null;
            }

            @Override
            public Exp visit(Not not) {
                return null;
            }

            @Override
            public Exp visit(Abs abs) {
                return null;
            }

            @Override
            public Exp visit(Fst fst) {
                return null;
            }

            @Override
            public Exp visit(Snd snd) {
                return null;
            }

            @Override
            public Exp visit(Projection projection) {
//                boundVariabels.add(new Var(projection.op));
                Exp projC = visitDispatch(projection.arg);
                projection.bound = projection.arg.bound;
                return projC;
            }

            @Override
            public Exp visit(ArraySelect arraySelect) {
                return null;
            }

            @Override
            public Exp visit(SetComplement setComplement) {
                return null;
            }

            @Override
            public Exp visit(SetCardinality setCardinality) {
                return null;
            }

            @Override
            public Exp visit(SingeltonTuple singeltonTuple) {
                return null;
            }

            @Override
            public  Exp visit(ArrayTypeConstructor arrayTypeConstructor){return  null;}
        }
        UOpV uOpV = new UOpV();
        @Override
        public Exp visit(UOp uOp) {
            return uOp.accept(uOpV);
        }


        class BOpV implements BOpVisitor<Exp>
        {
            @Override
            public Exp visit(Plus plus) {
                Exp c1 = visitDispatch(plus.arg1);
                Exp c2 = visitDispatch(plus.arg2);
                plus.bound = new Plus(plus.arg1.bound, plus.arg2.bound);
                return new And(c1, c2);
            }

            @Override
            public Exp visit(Minus minus) {
                Exp c1 = visitDispatch(minus.arg1);
                Exp c2 = visitDispatch(minus.arg2);
                minus.bound = new Plus(minus.arg1.bound, minus.arg2.bound);
                return new And(c1, c2);
            }

            @Override
            public Exp visit(Multiply multiply) {
                return null;
            }

            @Override
            public Exp visit(Eq eq) {
                Exp c1 = visitDispatch(eq.arg1);
                Exp c2 = visitDispatch(eq.arg2);
                eq.bound = new IntLiteral(0);

                And a = new And(
                        new Eq(eq.arg1.bound, new IntLiteral(0)),
                        new Eq(eq.arg2.bound, new IntLiteral(0))
                );
                return new And(new And(c1, c2), a);
            }

            @Override
            public Exp visit(NEq nEq) {
                return null;
            }

            @Override
            public Exp visit(Lt lt) {
                Exp c1 = visitDispatch(lt.arg1);
                Exp c2 = visitDispatch(lt.arg2);
                lt.bound = new IntLiteral(0);

                And a = new And(
                        new Eq(lt.arg1.bound, new IntLiteral(0)),
                        new Eq(lt.arg2.bound, new IntLiteral(0))
                );
                return new And(new And(c1, c2), a);
            }

            @Override
            public Exp visit(Gt gt) {
                Exp c1 = visitDispatch(gt.arg1);
                Exp c2 = visitDispatch(gt.arg2);
                gt.bound = new IntLiteral(0);

                And a = new And(
                        new Eq(gt.arg1.bound, new IntLiteral(0)),
                        new Eq(gt.arg2.bound, new IntLiteral(0))
                );
                return new And(new And(c1, c2), a);
            }

            @Override
            public Exp visit(Gte gte) {
                Exp c1 = visitDispatch(gte.arg1);
                Exp c2 = visitDispatch(gte.arg2);
                gte.bound = new IntLiteral(0);

                And a = new And(
                        new Eq(gte.arg1.bound, new IntLiteral(0)),
                        new Eq(gte.arg2.bound, new IntLiteral(0))
                );
                return new And(new And(c1, c2), a);
            }

            @Override
            public Exp visit(Min min) {
                return null;
            }

            @Override
            public Exp visit(Max max) {
                return null;
            }

            @Override
            public Exp visit(Pair pair) {
                return null;
            }

            @Override
            public Exp visit(And and) {
                Exp c1 = visitDispatch(and.arg1);
                Exp c2 = visitDispatch(and.arg2);
                and.bound = new IntLiteral(0);

                 And a = new And(
                                new Eq(and.arg1.bound, new IntLiteral(0)),
                                new Eq(and.arg2.bound, new IntLiteral(0))
                 );
                return new And(new And(c1, c2), a);
            }

            @Override
            public Exp visit(Or or) {
                return null;
            }

            @Override
            public Exp visit(ArrayMax arrayMax) {
                return null;
            }

            @Override
            public Exp visit(ArrayMin arrayMin) {
                return null;
            }

            @Override
            public Exp visit(SetUnion setUnion) {
                Exp c1 = visitDispatch(setUnion.arg1);
                Exp c2 = visitDispatch(setUnion.arg2);

                setUnion.bound = new Plus(setUnion.arg1.bound, setUnion.arg2.bound);
                return new And(c1, c2);
            }

            @Override
            public Exp visit(SetMinus setMinus) {
                Exp c1 = visitDispatch(setMinus.arg1);
                Exp c2 = visitDispatch(setMinus.arg2);
                setMinus.bound = new Plus(setMinus.arg1.bound, setMinus.arg2.bound);
                return new And(c1, c2);
            }

            @Override
            public Exp visit(SetIntersection setIntersection) {
                return null;
            }

            @Override
            public Exp visit(SetMembership setMembership) {
                return null;
            }

            @Override
            public Exp visit(SetSubset setSubset) {
                return null;
            }

            @Override
            public Exp visit(SetProduct setProduct) {
                Exp c1 = visitDispatch(setProduct.arg1);
                Exp c2 = visitDispatch(setProduct.arg2);
                setProduct.bound = new Multiply(setProduct.arg1.bound, setProduct.arg2.bound);
                multExp = (Multiply) setProduct.bound;
                return new And(c1, c2);
            }

            @Override
            public Exp visit(Implication implication) {
                return null;
            }
        }
        BOpV bOpV = new BOpV();
        @Override
        public Exp visit(BOp bOp) {
            return bOp.accept(bOpV);
        }

        @Override
        public Exp visit(TOp tOp) {
            return null;
        }

        class NOpV implements NOpVisitor<Exp>
        {
            @Override
            public Exp visit(RecordTypeConstructor recordTypeConstructor) {
                List<Exp> vars = new ArrayList<>();
                for (Map.Entry<String, Exp> entry : recordTypeConstructor.args.entrySet())
                    vars.add(new Var(entry.getKey()));
                return vars.stream().reduce(And::new).get();

            }
        }
        NOpV nOpV = new NOpV();
        @Override
        public Exp visit(NOp nOp) {
            return nOp.accept(nOpV);
        }

        @Override
        public Exp visit(ITE ite) {
            return null;
        }

        @Override
        public Exp visit(MatchOExp match) {
            return null;
        }

        @Override
        public Exp visit(Call call) {
            return null;
        }

        @Override
        public Exp visit(ArrayAssign arrayAssign) {
            return null;
        }

        @Override
        public Exp visit(Quantifier quantifier) {
            return null;
        }

        @Override
        public Exp visit(Tuple tuple) {
            return visitDispatch(tuple.exps[1]);
        }

        @Override
        public Exp visit(Selection selection) {
//            Exp pred = ((Return)selection.predicate.body).arg;
            Exp c1 = visitDispatch(selection.predicate);
            Exp c2 = visitDispatch(selection.relation);

            selection.bound = selection.relation.bound;
//            return new And(new And(c1, c2), new Eq(pred.bound, new IntLiteral(0)));
            return new And(c1, c2);
        }
    }
    AlgebraicExpV algebraicExpV = new AlgebraicExpV();
}
