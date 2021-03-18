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
            for a in range(n):
                alive.append(Implies(h[a] > 0, And(q[i2][a] < h[a], b[i1][a] < q[i2][a])))
            #constraints.append(Implies(sLe(q[i2], h), sL(b[i1], q[i2])))
            subconstr.append(And(alive))
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
#r1returnC = [ Bool('r1returnC_%s' % i) for i in range(n) ]
r1returnC = aC
r1returnI = [ [ Int("r1returnI_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
r1returnA = [ [ Int("r1returnA_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
#r2returnC = [ Bool('r2returnC_%s' % i) for i in range(n) ]
r2returnC = bC
r2returnI = [ [ Int("r2returnI_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
r2returnA = [ [ Int("r2returnA_%s_%s" % (i, j)) for j in range(n) ]
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

#for objects
r1Q1 = [ [ Int("r1Q1_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
r1Q2 = [ [ Int("r1Q2_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
r2Q1 = [ [ Int("r2Q1_%s_%s" % (i, j)) for j in range(n) ]
      for i in range(n) ]
r2Q2 = [ [ Int("r2Q2_%s_%s" % (i, j)) for j in range(n) ]
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

#declare constraints about the range
range1 = [And(0 <= m1H[i], 0 <= m2H[i]) for i in range(n)]
range2 = [ And(0 <= m1conxtI[i][j], 0 <= m1conxtA[i][j], 0 <= m2conxtI[i][j], 0 <= m2conxtA[i][j], 
              0 <= r1returnI[i][j], 0 <= r1returnA[i][j], 0 <= r2returnI[i][j], 0 <= r2returnA[i][j], 
              0 <= retQ[i][j], 0 <= m1Q[i][j], 0 <= m2Q[i][j], 0 <= r1Q1[i][j], 0 <= r1Q2[i][j], 0 <= r2Q1[i][j], 0 <= r2Q2[i][j], 
              0 <= r1inputI[i][j],  0 <= r1inputA[i][j], 0 <= r2inputI[i][j],  0 <= r2inputA[i][j])
             for i in range(n) for j in range(n) ]
range3 = [And(sLe(m1conxtI[i], principals), sLe(m1conxtA[i], principals), 
              sLe(m2conxtI[i], principals), sLe(m2conxtA[i], principals), 
              sLe(r1returnI[i], principals), sLe(r1returnA[i], principals), 
              sLe(r2returnI[i], principals), sLe(r2returnA[i], principals), 
              sLe(retQ[i], principals), sLe(m1Q[i], principals), sLe(m2Q[i], principals), 
              sLe(r1Q1[i], principals), sLe(r1Q2[i], principals), sLe(r2Q1[i], principals), sLe(r2Q2[i], principals),
              sLe(r1inputI[i], principals), sLe(r1inputA[i], principals), sLe(r2inputI[i], principals), sLe(r2inputA[i], principals))
         for i in range(n)]
s.add(range1)
s.add(range2)
s.add(range3)
s.add(And(sLe(m1H, principals), sLe(m2H, principals)))
s.add(And(Not(nonCheck(m1H)), Not(nonCheck(m2H))))

#add constraints from m1->res and m2->res (ThisallT)
s.add(lableLe(r1returnC, resultC, r1returnI, resultI, r1returnA, resultA))
#s.add(lableLe(m1conxtC, resultC, m1conxtI, resultI, m1conxtA, resultA))
s.add(availabilityP(resultA, retQ, m1H))
s.add(lableLe(r2returnC, resultC, r2returnI, resultI, r2returnA, resultA))
#s.add(lableLe(m2conxtC, resultC, m2conxtI, resultI, m2conxtA, resultA))
s.add(availabilityP(resultA, retQ, m2H))

#ObjCallT (r1.read() r2.read())
#s.add(lableLe(m1conxtC, r1inputC, m1conxtI, r1inputI, m1conxtA, r1inputA))
#s.add(availabilityP(r1inputA, r1Q2, m1H))
#s.add(lableLe(m2conxtC, r2inputC, m2conxtI, r2inputI, m2conxtA, r2inputA))
#s.add(availabilityP(r2inputA, r2Q2, m2H))

s.add(cLeH(r1returnC, m1H)) 
s.add(cLeH(r2returnC, m2H)) 


#FieldT (r1, r2)
#s.add(confQ(r1returnC, r1Q1))
#s.add(sIntegrity(r1returnI, r1Q1))
#s.add(availabilityC(r1returnA, r1Q1))
#s.add(cIntegrity(r1inputI, r1Q2, m1H))
s.add(cLe(aC, r1returnC)) 
#s.add(confQ(r2returnC, r2Q1))
#s.add(sIntegrity(r2returnI, r2Q1))
#s.add(availabilityC(r2returnA, r2Q1))
#s.add(cIntegrity(r2inputI, r2Q2, m2H))
s.add(cLe(bC, r2returnC))

#MethodT (res(x'))
s.add(cLeH(resultC, retH))
s.add(cIntegrity(resultI, retQ, m1H))
print(cIntegrity(resultI, retQ, m1H))
s.add(cIntegrity(resultI, retQ, m2H))

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
print("r1returnC:")
#print([is_true(m[r1c]) for r1c in r1returnC])
print(r1returnC)
print("r2returnC:")
#print([is_true(m[r2c]) for r2c in r2returnC])
print(r2returnC)
print("retQ:")
print([e for qs in retQ for e in qs])
print([m[e].as_long() for qs in retQ for e in qs])
