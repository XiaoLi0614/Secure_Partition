from z3 import *
#n = Int('n')
n = 3
principals = [4, 7, 1]
#principals = [ Int('p%s' % i) for i in range(n) ]
#principals = IntVector('principals', 3)
s = Solver()
#s = Optimize()

def solve(constr):
    s.add(constr)
    if s.check() == sat:
        return True
    else: 
        return False

def cLe(c1, c2):
    constraints = []
    for i1 in range(n):
        #constraints.append(And(Implies(c2[i1], c1[i1])))
        constraints.append(Implies(c2[i1], c1[i1]))
    result = And(constraints)
    return result

def cLeH (c1, h):
    constraints = []
    for i1 in range(n):
        #constraints.append(And(Implies(h[i1] > 0, c1[i1])))
        constraints.append(Implies(h[i1] > 0, c1[i1]))
    result = And(constraints)
    return result

def nonCheck(qs):
    constraints = []
    for i1 in range(n):
        #constraints.append(And(qs[i1] == 0))
        constraints.append(qs[i1] == 0)
    result = And(constraints)
    return result

def sLe(qs1, qs2):
    constraints = []
    for i1 in range(n):
        #constraints.append(And(qs2[i1] >= qs1[i1]))
        constraints.append(qs2[i1] >= qs1[i1])
    result = And(constraints)
    return result

def sL(qs1, qs2):
    constraints = []
    for i1 in range(n):
        constraints.append(qs1[i1] < qs2[i1])
    result = Implies(Not(nonCheck(qs2)), Or(constraints))
    return result

def bLe(q1, q2):
    constraints = []
    for i1 in range(n):
        subconstr = []
        for i2 in range(n):
            subconstr.append(sLe(q1[i1], q2[i2]))   
        constraints.append(Or(subconstr))
    result = And(constraints)
    return result

def confQ(c, q):
    constraints = []
    for i1 in range(n):
        for i2 in range(n):
            constraints.append(Implies(q[i1][i2] > 0, c[i2]))
    result = And(constraints)
    return result

#There are two solutions about representation of the Q matrix:
#1. the nonCheck can be eliminated because of integrity constraint. Then we will have to deal with redundency
#2. add nonCheck and then [[4, 7, 1],[0, 0, 0],[0, 0, 0]] can exists
def availabilityC(b, q):
    constraints = []
    for i1 in range(n):
        subconstr = []
        for i2 in range(n):
            aliveQs = [ principals[i] - b[i1][i] for i in range(n) ]
            subconstr.append(And(sLe(q[i2], aliveQs), Not(nonCheck(q[i2]))))
        constraints.append(Or(subconstr))
    result = And(constraints)
    return result

def availabilityP(b, q, h):
    constraints = []
    for i1 in range(n):
        subconstr = []
        for i2 in range(n):
            alive = []
            for a in range(n):
                alive.append(Implies(h[a] > 0, q[i2][a] <= h[a] - b[i1][a]))
            #aliveQs = []
            #for a in range(n):
                #if h[a] == 0: 
                    #aliveQs.append(0) 
                #else: 
                    #aliveQs.append(h[a] - b[i1][a])
            #subconstr.append(And(sLe(q[i2], aliveQs), Not(nonCheck(q[i2]))))
            subconstr.append(Implies(Not(nonCheck(q[i2])), And(alive)))
        constraints.append(Or(subconstr))
    result = And(constraints)
    return result

def cIntegrity(b, q, h):
    constraints = []
    for i1 in range(n):
        subconstr = []
        for i2 in range(n):
            alive = []
            #filterE = []
            for a in range(n):
                alive.append(Implies(h[a] > 0, And(q[i2][a] < h[a], b[i1][a] < q[i2][a])))
                #filterE.append(Implies(h[a] == 0, q[i2][a] == 0))
            subconstr.append(And(alive))
            #subconstr.append(And(filterE))
        constraints.append(And(subconstr))
    result = And(constraints)
    return result

def cIntegrityE(b, q, h):
    constraints = []
    for i1 in range(n):
        subconstr = []
        for i2 in range(n):
            alive = []
            #filterE = []
            for a in range(n):
                alive.append(Implies(h[a] > 0, And(q[i2][a] <= h[a], b[i1][a] < q[i2][a])))
                #filterE.append(Implies(h[a] == 0, q[i2][a] == 0))
            subconstr.append(And(alive))
            #subconstr.append(And(filterE))
        constraints.append(And(subconstr))
    result = And(constraints)
    return result

def sIntegrity(b, q):
    constraints = []
    for i3 in range(n):
        for i1 in range(n):
            for i2 in range(n):
                for j in range(n):
                    constraints.append(Implies(And(Not(q[i1][j] == 0), Not(q[i2][j] == 0)), 
                                               ((q[i1][j] + q[i2][j] - principals[j]) > b[i3][j])))
    result = And(constraints)
    return result

def lableLe(c1, c2, i1, i2, a1, a2):
    return And(cLe(c1, c2), bLe(i2, i1), bLe(a2, a1))
    

#initialize the final type tau
resultC = [ False, False, True ]
resultI = [[1, 2, 0], [0, 0, 0], [0, 0, 0]]
resultA = [[1, 2, 0], [0, 0, 0], [0, 0, 0]]

#initialize the context type tau_0
startC = [ True, True, True ]
startI = [[4, 7, 0], [0, 0, 0], [0, 0, 0]]
startA = [[4, 7, 0], [0, 0, 0], [0, 0, 0]]

#initialize other user defined type variables
aC = [True, False, True]
bC = [False, True, True]

#declare the type variables
m1conxtC = [ Bool('m1conxtC_%s' % i) for i in range(n) ]
m1conxtI = [ [ Int("m1conxtI_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
m1conxtA = [ [ Int("m1conxtA_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
m2conxtC = [ Bool('m2conxtC_%s' % i) for i in range(n) ]
m2conxtI = [ [ Int("m2conxtI_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
m2conxtA = [ [ Int("m2conxtA_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
m3conxtC = [ Bool('m3conxtC_%s' % i) for i in range(n) ]
m3conxtI = [ [ Int("m3conxtI_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
m3conxtA = [ [ Int("m3conxtA_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
m4conxtC = [ Bool('m4conxtC_%s' % i) for i in range(n) ]
m4conxtI = [ [ Int("m4conxtI_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
m4conxtA = [ [ Int("m4conxtA_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]

m1botC = m1conxtC
m1botI = m1conxtI
m1botA = m1conxtA
m2botC = m2conxtC
m2botI = m2conxtI
m2botA = m2conxtA
m3xC = [ Bool('m3xC_%s' % i) for i in range(n) ]
m3xI = [ [ Int("m3xI_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
m3xA = [ [ Int("m3xA_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
m4xC = [ Bool('m4xC_%s' % i) for i in range(n) ]
m4xI = [ [ Int("m4xI_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
m4xA = [ [ Int("m4xA_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]

r1returnC = [ Bool('r1returnC_%s' % i) for i in range(n) ]
#r1returnC = aC
r1returnI = [ [ Int("r1returnI_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
r1returnA = [ [ Int("r1returnA_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
r2returnC = [ Bool('r2returnC_%s' % i) for i in range(n) ]
#r2returnC = bC
r2returnI = [ [ Int("r2returnI_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
r2returnA = [ [ Int("r2returnA_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
rreturn1C = [ Bool('rreturn1C_%s' % i) for i in range(n) ]
#r1returnC = aC
rreturn1I = [ [ Int("rreturn1I_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
rreturn1A = [ [ Int("rreturn1A_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
rreturn2C = [ Bool('rreturn2C_%s' % i) for i in range(n) ]
#r2returnC = bC
rreturn2I = [ [ Int("rreturn2I_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
rreturn2A = [ [ Int("rreturn2A_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]

#initialize the placement information for methods
#retH = [ Int('retH_%s' % i) for i in range(n) ]
retH = [0, 0, 1]
retQ = [ [ Int("retQ_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
m1H = [ Int('m1H_%s' % i) for i in range(n) ]
m1Q = [ [ Int("m1Q_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
m2H = [ Int('m2H_%s' % i) for i in range(n) ]
m2Q = [ [ Int("m2Q_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
m3H = [ Int('m3H_%s' % i) for i in range(n) ]
m3Q = [ [ Int("m3Q_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
m4H = [ Int('m4H_%s' % i) for i in range(n) ]
m4Q = [ [ Int("m4Q_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]

#for objects
r1Q1 = [ [ Int("r1Q1_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
r1Q2 = [ [ Int("r1Q2_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
r2Q1 = [ [ Int("r2Q1_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
r2Q2 = [ [ Int("r2Q2_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
rQ1 = [ [ Int("rQ1_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
rQ2 = [ [ Int("rQ2_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]

#for objects methods
r1inputC = [ Bool('r1inputC_%s' % i) for i in range(n) ]
r1inputI = [ [ Int("r1inputI_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
r1inputA = [ [ Int("r1inputA_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
r2inputC = [ Bool('r2inputC_%s' % i) for i in range(n) ]
r2inputI = [ [ Int("r2inputI_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
r2inputA = [ [ Int("r2inputA_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
rinput1C = [ Bool('rinput1C_%s' % i) for i in range(n) ]
rinput1I = [ [ Int("rinput1I_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
rinput1A = [ [ Int("rinput1A_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
rinput2C = [ Bool('rinput2C_%s' % i) for i in range(n) ]
rinput2I = [ [ Int("rinput2I_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
rinput2A = [ [ Int("rinput2A_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]

# for input variables
#xC = [ Bool('xC_%s' % i) for i in range(n) ]
#xI = [ [ Int("xI_%s_%s" % (i, j)) for j in range(n) ]
#      for i in range(n) ]
#xA = [ [ Int("xA_%s_%s" % (i, j)) for j in range(n) ]
#      for i in range(n) ]
xC = startC
xI = startI
xA = startA

#declare constraints about the range
range1 = [And(0 <= m1H[i], 0 <= m2H[i], 0 <= m3H[i], 0 <= m4H[i]) for i in range(n)]
range2 = [ And(0 <= m1conxtI[i][j], 0 <= m1conxtA[i][j], 0 <= m2conxtI[i][j], 0 <= m2conxtA[i][j], 
               0 <= m3conxtI[i][j], 0 <= m3conxtA[i][j], 0 <= m4conxtI[i][j], 0 <= m4conxtA[i][j], 
              0 <= r1returnI[i][j], 0 <= r1returnA[i][j], 0 <= r2returnI[i][j], 0 <= r2returnA[i][j], 
              0 <= rreturn1I[i][j], 0 <= rreturn1A[i][j], 0 <= rreturn2I[i][j], 0 <= rreturn2A[i][j], 
              0 <= retQ[i][j], 0 <= m1Q[i][j], 0 <= m2Q[i][j], 0 <= m3Q[i][j], 0 <= m4Q[i][j],
              0 <= r1Q1[i][j], 0 <= r1Q2[i][j], 0 <= r2Q1[i][j], 0 <= r2Q2[i][j], 0 <= rQ1[i][j], 0 <= rQ2[i][j], 
              0 <= r1inputI[i][j],  0 <= r1inputA[i][j], 0 <= r2inputI[i][j],  0 <= r2inputA[i][j],
              0 <= rinput1I[i][j],  0 <= rinput1A[i][j], 0 <= rinput2I[i][j],  0 <= rinput2A[i][j], 
              0 <= m3xI[i][j],  0 <= m3xA[i][j], 0 <= m4xI[i][j],  0 <= m4xA[i][j])
             for i in range(n) for j in range(n) ]
range3 = [And(sLe(m1conxtI[i], principals), sLe(m1conxtA[i], principals), 
              sLe(m2conxtI[i], principals), sLe(m2conxtA[i], principals),
              sLe(m3conxtI[i], principals), sLe(m3conxtA[i], principals), 
              sLe(m4conxtI[i], principals), sLe(m4conxtA[i], principals),
              sLe(r1returnI[i], principals), sLe(r1returnA[i], principals), 
              sLe(r2returnI[i], principals), sLe(r2returnA[i], principals),
              sLe(rreturn1I[i], principals), sLe(rreturn1A[i], principals), 
              sLe(rreturn2I[i], principals), sLe(rreturn2A[i], principals), 
              sLe(retQ[i], principals), sLe(m1Q[i], principals), sLe(m2Q[i], principals), sLe(m3Q[i], principals), sLe(m4Q[i], principals),
              sLe(r1Q1[i], principals), sLe(r1Q2[i], principals), sLe(r2Q1[i], principals), sLe(r2Q2[i], principals),
              sLe(r1inputI[i], principals), sLe(r1inputA[i], principals), sLe(r2inputI[i], principals), sLe(r2inputA[i], principals),
              sLe(rQ1[i], principals), sLe(rQ2[i], principals),
              sLe(rinput1I[i], principals), sLe(rinput1A[i], principals), sLe(rinput2I[i], principals), sLe(rinput2A[i], principals), 
              sLe(m3xI[i], principals), sLe(m3xA[i], principals), sLe(m4xI[i], principals), sLe(m4xA[i], principals))
         for i in range(n)]

s.add(range1)
s.add(range2)
s.add(range3)
s.add(And(sLe(m1H, principals), sLe(m2H, principals), sLe(m3H, principals), sLe(m4H, principals)))
s.add(And(Not(nonCheck(m1H)), Not(nonCheck(m2H)), Not(nonCheck(m3H)), Not(nonCheck(m4H))))

#add constraints from m1->res and m2->res (ThisallT)
s.add(lableLe(r1returnC, resultC, r1returnI, resultI, r1returnA, resultA))
s.add(lableLe(m1conxtC, resultC, m1conxtI, resultI, m1conxtA, resultA))
s.add(availabilityP(resultA, retQ, m1H))
s.add(lableLe(r2returnC, resultC, r2returnI, resultI, r2returnA, resultA))
s.add(lableLe(m2conxtC, resultC, m2conxtI, resultI, m2conxtA, resultA))
s.add(availabilityP(resultA, retQ, m2H))

#ObjCallT (r1.read() r2.read())
s.add(lableLe(m1conxtC, r1inputC, m1conxtI, r1inputI, m1conxtA, r1inputA))
s.add(availabilityP(r1inputA, r1Q2, m1H))
s.add(lableLe(m2conxtC, r2inputC, m2conxtI, r2inputI, m2conxtA, r2inputA))
s.add(availabilityP(r2inputA, r2Q2, m2H))

s.add(cLeH(r1returnC, m1H)) 
s.add(cLeH(r2returnC, m2H)) 

#FieldT (r1, r2)
s.add(confQ(r1returnC, r1Q1))
s.add(sIntegrity(r1returnI, r1Q1))
s.add(availabilityC(r1returnA, r1Q1))
s.add(cIntegrity(r1inputI, r1Q2, m1H))
s.add(cLe(aC, r1returnC)) 
s.add(confQ(r2returnC, r2Q1))
s.add(sIntegrity(r2returnI, r2Q1))
s.add(availabilityC(r2returnA, r2Q1))
s.add(cIntegrity(r2inputI, r2Q2, m2H))
s.add(cLe(bC, r2returnC))

#MethodT (res(x'))
s.add(cLeH(resultC, retH))
s.add(cIntegrity(resultI, retQ, m1H))
s.add(cIntegrity(resultI, retQ, m2H))

#MethodT(m1(\bot), m2(\bot))
s.add(cLeH(m1conxtC, m1H))
s.add(cIntegrity(m1botI, m1Q, m3H))
s.add(cLeH(m2conxtC, m2H))
s.add(cIntegrity(m2botI, m2Q, m3H))

#ObjCallT(r.write(true))
s.add(lableLe(m3conxtC, rinput2C, m3conxtI, rinput2I, m3conxtA, rinput2A))
s.add(availabilityP(rinput2A, rQ2, m3H))

s.add(cLeH(rreturn2C, m3H))

#ThisCallT(m1(\bot), m2(\bot))
s.add(lableLe(m3conxtC, m1conxtC, m3conxtI, m1conxtI, m3conxtA, m1conxtA))
s.add(lableLe(m3xC, m1conxtC, m3xI, m1conxtI, m3xA, m1conxtA))
s.add(lableLe(m3conxtC, m1botC, m3conxtI, m1botI, m3conxtA, m1botA))
s.add(lableLe(m3xC, m1botC, m3xI, m1botI, m3xA, m1botA))
s.add(availabilityP(m1botA, m1Q, m3H))
s.add(bLe(m1conxtA, rreturn2A))
s.add(bLe(m1botA, rreturn2A))

#s.add(And(m1botC == m1conxtC, m1botI == m1conxtI, m1botA == m1conxtA))

s.add(lableLe(m3conxtC, m2conxtC, m3conxtI, m2conxtI, m3conxtA, m2conxtA))
s.add(lableLe(m3xC, m2conxtC, m3xI, m2conxtI, m3xA, m2conxtA))
s.add(lableLe(m3conxtC, m2botC, m3conxtI, m2botI, m3conxtA, m2botA))
s.add(lableLe(m3xC, m2botC, m3xI, m2botI, m3xA, m2botA))
s.add(availabilityP(m2botA, m2Q, m3H))
s.add(bLe(m2conxtA, rreturn2A))
s.add(bLe(m2botA, rreturn2A))

#s.add(And(m2botC == m2conxtC, m2botI == m2conxtI, m2botA == m2conxtA))

#IfT: if x then m1(\bot) else m2(\bot)
#the only constraint is about return type but is never used(ignored for now)

#SeqT: r.write(true) if x then m1(\bot) else m2(\bot)
#the only constraint is about return type but is never used(ignored for now)

#MethodT(m3(x))
s.add(cLeH(m3conxtC, m3H))
s.add(cIntegrity(m3xI, m3Q, m4H))

#ObjCallT(r.read())
s.add(lableLe(m4conxtC, rinput1C, m4conxtI, rinput1I, m4conxtA, rinput1A))
s.add(availabilityP(rinput1A, rQ2, m4H))

s.add(cLeH(rreturn1C, m4H))

#ThisCallT(m3(x))
s.add(lableLe(m4conxtC, m3conxtC, m4conxtI, m3conxtI, m4conxtA, m3conxtA))
s.add(lableLe(rreturn1C, m3conxtC, rreturn1I, m3conxtI, rreturn1A, m3conxtA))
s.add(lableLe(m4conxtC, m3xC, m4conxtI, m3xI, m4conxtA, m3xA))
s.add(lableLe(rreturn1C, m3xC, rreturn1I, m3xI, rreturn1A, m3xA))
s.add(lableLe(m4xC, m3xC, m4xI, m3xI, m4xA, m3xA))
s.add(availabilityP(m3xA, m3Q, m4H))

#ThisCall(res(0))
s.add(lableLe(m4conxtC, resultC, m4conxtI, resultI, m4conxtA, resultA))
s.add(lableLe(rreturn1C, resultC, rreturn1I, resultI, rreturn1A, resultA))
s.add(availabilityP(resultA, retQ, m4H)) # take extra care in this assertion

#IfT: if r.read() then m3(x) else res(0)
#the only constraint is about return type but is never used(ignored for now)

#MethodT(m4(x))
s.add(cLeH(m4conxtC, m4H))
s.add(cIntegrityE(m4xI, m4Q, retH))

#ThisCallT(m4(x))
s.add(lableLe(startC, m4conxtC, startI, m4conxtI, startA, m4conxtA))
s.add(lableLe(xC, m4xC, xI, m4xI, xA, m4xA))
s.add(lableLe(startC, m4xC, startI, m4xI, startA, m4xA))
s.add(availabilityP(m4xA, m4Q, retH))

#s.add(And(startC == m4xC, startI == m4xI, startA ==m4xA))

#FieldT: r
s.add(lableLe(rinput1C, rreturn1C, rinput1I, rreturn1I, rinput1A, rreturn1A))
s.add(lableLe(rinput2C, rreturn2C, rinput2I, rreturn2I, rinput2A, rreturn2A))
s.add(confQ(rreturn1C, rQ1))
s.add(confQ(rreturn2C, rQ1))
s.add(sIntegrity(rreturn1I, rQ1))
s.add(sIntegrity(rreturn2I, rQ1))
s.add(availabilityC(rreturn1A, rQ1))
s.add(availabilityC(rreturn2A, rQ1))
s.add(cIntegrity(rinput1I, rQ2, m4H))
s.add(cIntegrity(rinput2I, rQ2, m3H))


#s.minimize(sum(m1H) + sum(m2H))
print(s.check())
#print(s.unsat_core())
m = s.model()
print("retH:")
print(retH)
print("m1H:")
print([m[m1h].as_long() for m1h in m1H])
print("m2H:")
print([m[m2h].as_long() for m2h in m2H])
print("m3H:")
print([m[m3h].as_long() for m3h in m3H])
print("m4H:")
print([m[m4h].as_long() for m4h in m4H])

print("r1returnC:")
print([is_true(m[r1c]) for r1c in r1returnC])
#print(r1returnC)
print("r2returnC:")
print([is_true(m[r2c]) for r2c in r2returnC])
#print(r2returnC)
print("rreturn1C:")
print([is_true(m[rc1]) for rc1 in rreturn1C])
print("rreturn2C:")
print([is_true(m[rc2]) for rc2 in rreturn2C])

print("retQ:")
print([e for qs in retQ for e in qs])
print([m[e].as_long() for qs in retQ for e in qs])
print("m1Q:")
print([e for qs in m1Q for e in qs])
print([m[e].as_long() for qs in m1Q for e in qs])
print("m2Q:")
print([e for qs in m2Q for e in qs])
print([m[e].as_long() for qs in m2Q for e in qs])
print("m3Q:")
print([e for qs in m3Q for e in qs])
print([m[e].as_long() for qs in m3Q for e in qs])
print("m4Q:")
print([e for qs in m4Q for e in qs])
print([m[e].as_long() for qs in m4Q for e in qs])

print("r1Q1:")
print([e for qs in r1Q1 for e in qs])
print([m[e].as_long() for qs in r1Q1 for e in qs])
print("r2Q1:")
print([e for qs in r2Q1 for e in qs])
print([m[e].as_long() for qs in r2Q1 for e in qs])
print("rQ1:")
print([e for qs in rQ1 for e in qs])
print([m[e].as_long() for qs in rQ1 for e in qs])

#print("m4xC:")
#print([is_true(m[m4xc]) for m4xc in m4xC])
#print("m4xI:")
#print([e for qs in m4xI for e in qs])
#print([m[e].as_long() for qs in m4xI for e in qs])
#print("m3xC:")
#print([is_true(m[m3xc]) for m3xc in m3xC])
#print("m3xI:")
#print([e for qs in m3xI for e in qs])
#print([m[e].as_long() for qs in m3xI for e in qs])
#print("m4conxtI:")
#print([e for qs in m4conxtI for e in qs])
#print([m[e].as_long() for qs in m4conxtI for e in qs])
