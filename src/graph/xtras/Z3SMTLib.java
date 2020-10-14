//package graph.xtras;
//
//import com.microsoft.z3.*;
//import graph.lang.ast.Var;
//import lesani.collection.option.None;
//import lesani.collection.option.Option;
//import lesani.collection.option.Some;
//import util.Logger;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.*;
//
//public class Z3SMTLib {
//
//   private static int timeout = 300000;
//   static int id = 1;
//
//   static Context Z3Context;
//
//   public static Context getZ3Context() {
//      if (Z3Context == null)
//         Z3Context = new Context();
//      return Z3Context;
//
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
//   // ----------------------------------------------
//   public static boolean sat(String smtlib, boolean useMBQI, int timeout) {
//      Context ctx = new Context();
//      Solver s = ctx.mkSolver();
//      Params p = ctx.mkParams();
//      p.add("mbqi", useMBQI);
//      p.add("timeout", timeout);
//      s.setParameters(p);
//
//      BoolExpr[] expr = ctx.parseSMTLIB2String(smtlib,
//              null, null, null, null);
//
//      s.add(expr);
//
//      Status q = s.check();
//      return (q == Status.SATISFIABLE);
//      // s.getModel()
//      // Todo: Cam wite function disproveAndGetModel returns option of Model
//      // Evaluation on a model
//      // Expr v = model.evaluate(ctx.mkAdd(x, y), false);
//   }
//
//   public static boolean sat(String smtlib) {
//      return sat(smtlib,false, timeout);
//   }
//
//   // ----------------------------------------------
//   public static boolean prove(String smtlib, String conjecture, boolean useMBQI, int timeout) {
//      Random rand= new Random();
//      Context ctx = new Context();
//      Solver s = ctx.mkSolver();
//      Params p = ctx.mkParams();
////      p.add(".mbqi", useMBQI);
//      p.add("timeout", timeout);
//      //smt.soft_timeout
//      s.setParameters(p);
//
//      String all = smtlib +
//              "(assert (not " + conjecture + "))\n";
//
//      String outfile = "Lib/Out.smt2";
////      String outfile = "Lib/auction/dependency"+id+".smt2";
//
//      try {
//         FileWriter fileWriter = new FileWriter(outfile);
//         fileWriter.write(all);
//         fileWriter.close();
//      } catch (IOException e) {
//         e.printStackTrace();
//      }
//
//      BoolExpr[] expr = ctx.parseSMTLIB2String(all,
//              null, null, null, null);
//
//      s.add(expr);
//      Status q = s.check();
//      id++;
//
//      System.out.println(q);
//      return (q == Status.UNSATISFIABLE);
//      // s.getProof()
//      // Todo: Can write functiond proveAndGetProof returns option of Proof
//   }
//
//
//   public static boolean prove(String smtlib, String conjecture, boolean useMBQI, int timeout, String filename) {
////      Random rand= new Random();
////      Context ctx = new Context();
////      Solver s = ctx.mkSolver();
////      Params p = ctx.mkParams();
//////      p.add(".mbqi", useMBQI);
////      p.add("timeout", timeout);
////      //smt.soft_timeout
////      s.setParameters(p);
//
//      String all = smtlib +
//              "ASSERT NOT " + conjecture + "\n";
//
//      String outfile = "Lib/cvc/courseware/commutativity/"+filename+".cvc4";
////      String outfile = "Lib/auction/dependency"+id+".smt2";
//
//      try {
//         FileWriter fileWriter = new FileWriter(outfile);
//         fileWriter.write(all);
//         fileWriter.write("CHECKSAT;");
////         fileWriter.write("COUNTERMODEL;");
//         fileWriter.close();
//      } catch (IOException e) {
//         e.printStackTrace();
//      }
//
////      BoolExpr expr = ctx.parseSMTLIB2String(all,
////              null, null, null, null);
//
////      s.add(expr);
////      Status q = s.check();
//      id++;
//
////      System.out.println(q);
////      return (q == Status.UNSATISFIABLE);
//      return true;
//      // s.getProof()
//      // Todo: Can write functiond proveAndGetProof returns option of Proof
//   }
//
//
//   public static boolean prove(String smtlib, String conjecture) {
//      return prove(smtlib, conjecture, false, timeout);
//   }
//
//   public static HashMap<Var, Option<Integer>> maximize(BoolExpr smtlib, Set<Var> stateVars)
//   {
//      Context ctx = getZ3Context();
//      Optimize opt = ctx.mkOptimize();
//      IntExpr[] intExprs = new IntExpr[stateVars.size()];
//      Iterator<Var> stateVarIter = stateVars.iterator();
//      for (int i = 0; i < stateVars.size(); i++)
//      {
//         Var sVar = stateVarIter.next();
//         intExprs[i] = ctx.mkIntConst(sVar.name);
//         opt.Assert(ctx.mkGe(intExprs[i], ctx.mkInt(0)));
//      }
//
//      opt.Assert((BoolExpr)smtlib.simplify());
//
//      HashMap<Var, Option<Integer>> objectivesMap = new HashMap<>();
//      opt.MkMaximize(ctx.mkAdd(intExprs));
//      for (Var av : stateVars) {
//         opt.Push();
//         Optimize.Handle handle = opt.MkMaximize(ctx.mkApp(ctx.mkFuncDecl(av.name, new Sort[]{}, ctx.mkIntSort())));
//         opt.Check();
//         if(opt.Check() == Status.UNSATISFIABLE)
//            return null;
//         if(Logger.log) {
//            System.out.println("Context for state element " + av.name + ": ");
//            System.out.println(opt);
//            System.out.println("Inferred max bound for " + av.name + ": ");
//            System.out.println(handle.toString());
//         }
//         objectivesMap.put(av, !handle.toString().contains("oo") ? new Some<Integer>(Integer.valueOf(handle.toString())) : None.instance());
//         opt.Pop();
//      }
//
//      return objectivesMap;
//   }
//
//   public static boolean prove(String smtlib, String conjecture, String filename) {
//      return prove(smtlib, conjecture, false, timeout, filename);
//   }
//   public static boolean prove(String smtlib, String conjecture, int timeout) {
//      return prove(smtlib, conjecture, false, timeout);
//   }
//
//   // ----------------------------------------------
//
//   public static boolean disprove(String smtlib, String conjecture, boolean useMBQI, int timeout) {
//      Context ctx = new Context();
//      Solver s = ctx.mkSolver();
//      Params p = ctx.mkParams();
//      p.add("mbqi", useMBQI);
//      p.add("timeout", timeout);
//      s.setParameters(p);
//
//
//      String all = smtlib +
//              "(assert (not " + conjecture + "))\n";
//
//      BoolExpr[] expr = ctx.parseSMTLIB2String(all,
//              null, null, null, null);
//
//      s.add(expr);
//
//      Status q = s.check();
//      return (q == Status.SATISFIABLE);
//      // s.getModel()
//      // Todo: Can wite function disproveAndGetModel returns option of Model
//      // Evaluation on a model
//      // Expr v = model.evaluate(ctx.mkAdd(x, y), false);
//   }
//
//
//   public static boolean disprove(Context ctx, String smtlib, String conjecture) {
//      return disprove(smtlib, conjecture, false, timeout);
//   }
//
//
//   // ----------------------------------------------
//
//
//
//
//}