//package graph.xtras;
//
//import com.microsoft.z3.*;
//
//import java.util.HashMap;
//
//public class Z3 {
//
//   public static Context newContext() {
//      return new Context();
//   }
//   public static Context newModelGenContext() {
//      HashMap<String, String> cfg = new HashMap<String, String>();
//      cfg.put("model", "true");
//      return new Context(cfg);
//   }
//   public static Context newProofProdContext() {
//      HashMap<String, String> cfg = new HashMap<String, String>();
//      cfg.put("proof", "true");
//      return new Context(cfg);
//   }
//
//   public static boolean prove(Context ctx, BoolExpr[] assumptions, BoolExpr f, boolean useMBQI, int timeout) {
//
//      Solver s = ctx.mkSolver();
//      Params p = ctx.mkParams();
//      p.add("mbqi", useMBQI);
//      p.add("timeout", timeout);
//      //smt.soft_timeout
//      s.setParameters(p);
//
//      for (BoolExpr a : assumptions)
//         s.add(a);
//
//      s.add(ctx.mkNot(f));
//
//      Status q = s.check();
//
//      return (q == Status.UNSATISFIABLE);
//      // s.getProof()
//      // Todo: Can write functiond proveAndGetProof returns option of Proof
//   }
//
//   public static boolean prove(Context ctx, BoolExpr g, boolean useMBQI, int timeout) {
//      BoolExpr[] assumptions = new BoolExpr[0];
//      return prove(ctx, assumptions, g, useMBQI, timeout);
//   }
//
//   public static boolean prove(Context ctx, BoolExpr g, int timeout) {
//      return prove(ctx, g, timeout);
//   }
//
//   public static boolean prove(Context ctx, BoolExpr[] assumptions, BoolExpr g, int timeout) {
//      return prove(ctx, assumptions, g, false, timeout);
//   }
//
//   // ----------------------------------------------
//
//   public static boolean sat(Context ctx, BoolExpr[] fs, boolean useMBQI, int timeout) {
//      Solver s = ctx.mkSolver();
//      Params p = ctx.mkParams();
//      p.add("mbqi", useMBQI);
//      p.add("timeout", timeout);
//      s.setParameters(p);
//
//      for (BoolExpr f : fs)
//         s.add(f);
//
//      Status q = s.check();
//      return (q == Status.SATISFIABLE);
//      // s.getModel()
//      // Todo: Cam wite function disproveAndGetModel returns option of Model
//      // Evaluation on a model
//      // Expr v = model.evaluate(ctx.mkAdd(x, y), false);
//   }
//
//   public static boolean sat(Context ctx, BoolExpr f, boolean useMBQI, int timeout) {
//      BoolExpr[] a = {f};
//      return sat(ctx, a, useMBQI, timeout);
//   }
//
//   public static boolean sat(Context ctx, BoolExpr f, int timeout) {
//      return sat(ctx, f, false, timeout);
//   }
//
//   public static boolean sat(Context ctx, BoolExpr[] assumptions, int timeout) {
//      return sat(ctx, assumptions,false, timeout);
//   }
//
//   public static boolean disprove(Context ctx, BoolExpr[] assumptions, BoolExpr f, boolean useMBQI, int timeout) {
//      Solver s = ctx.mkSolver();
//      Params p = ctx.mkParams();
//      p.add("mbqi", useMBQI);
//      p.add("timeout", timeout);
//      s.setParameters(p);
//
//      for (BoolExpr a : assumptions)
//         s.add(a);
//
//      s.add(ctx.mkNot(f));
//
//      Status q = s.check();
//      return (q == Status.SATISFIABLE);
//      // s.getModel()
//      // Todo: Cam wite function disproveAndGetModel returns option of Model
//      // Evaluation on a model
//      // Expr v = model.evaluate(ctx.mkAdd(x, y), false);
//   }
//
//
//   public static boolean disprove(Context ctx, BoolExpr f, boolean useMBQI, int timeout) {
//      BoolExpr[] a = {};
//      return disprove(ctx, a, f, useMBQI, timeout);
//   }
//
//   public static boolean disprove(Context ctx, BoolExpr f, int timeout) {
//      return disprove(ctx, f, false, timeout);
//   }
//
//   public static boolean disprove(Context ctx, BoolExpr[] assumptions, BoolExpr f, int timeout) {
//      return disprove(ctx, assumptions, f,false, timeout);
//   }
//
//   // ----------------------------------------------
//   public static BoolExpr[] parse(Context ctx, String text) {
//      return ctx.parseSMTLIB2String(text, null, null, null, null);
//   }
//
//   public static BoolExpr[] parseFile(Context ctx, String filename) {
//      return ctx.parseSMTLIB2File(filename, null, null, null, null);
//   }
//
//
///*
//
//   public void optimize(Context ctx) {
//
//      Optimize opt = ctx.mkOptimize();
//
//      // Set constraints.
//      IntExpr xExp = ctx.mkIntConst("x");
//      IntExpr yExp = ctx.mkIntConst("y");
//
//      opt.Add(ctx.mkEq(ctx.mkAdd(xExp, yExp), ctx.mkInt(10)),
//            ctx.mkGe(xExp, ctx.mkInt(0)),
//            ctx.mkGe(yExp, ctx.mkInt(0)));
//
//      // Set objectives.
//      Optimize.Handle mx = opt.MkMaximize(xExp);
//      Optimize.Handle my = opt.MkMaximize(yExp);
//
//      System.out.println(opt.Check());
//      System.out.println(mx);
//      System.out.println(my);
//   }
//   // Todo: revise optimize.
//*/
//
//   public static void test1() {
//      Context ctx = newContext();
//
//      IntExpr xExp = ctx.mkIntConst("x");
//      IntExpr yExp = ctx.mkIntConst("y");
//
//      BoolExpr[] as = {
//            ctx.mkEq(ctx.mkAdd(xExp, yExp), ctx.mkInt(10)),
//            ctx.mkGe(xExp, ctx.mkInt(0)),
//            ctx.mkGe(yExp, ctx.mkInt(0))
//      };
//
//      if (sat(ctx, as, 2))
//         System.out.println("Test passed");
//      else
//         System.out.println("Test failed");
//   }
//
//   public static void test2() {
//      Context ctx = newContext();
//
//      IntExpr xExp = ctx.mkIntConst("x");
//      IntExpr yExp = ctx.mkIntConst("y");
//
//      BoolExpr[] as = {
//            ctx.mkGe(xExp, ctx.mkInt(10)),
//            ctx.mkGe(yExp, xExp)
//      };
//      BoolExpr f = ctx.mkGe(xExp, ctx.mkInt(10));
//
//      if (prove(ctx, as, f,2))
//         System.out.println("Test passed");
//      else
//         System.out.println("Test failed");
//   }
//
//   public static void test3() {
//      Context ctx = newContext();
//
//      IntExpr xExp = ctx.mkIntConst("x");
//      IntExpr yExp = ctx.mkIntConst("y");
//
//      BoolExpr[] as = {
//            ctx.mkGe(xExp, ctx.mkInt(10)),
//            ctx.mkGe(yExp, xExp)
//      };
//      BoolExpr f = ctx.mkGe(yExp, ctx.mkInt(11));
//
//      if (disprove(ctx, as, f,2))
//         System.out.println("Test passed");
//      else
//         System.out.println("Test failed");
//   }
//
//
//   public static void main(String[] args) {
//      test1();
//      test2();
//      test3();
//      test4();
//   }
//
//   public static void test4() {
//      Context ctx = Z3.newContext();
//      BoolExpr[] expr = Z3.parseFile(ctx, "Lib/Lib.smt2");
//
//
//
//   }
//
//}
//
//
///*
//* Convert model is used to convert the model found for a goal resulted from a tactic to a model for the original goal.
//*
//* */
//
//
//
