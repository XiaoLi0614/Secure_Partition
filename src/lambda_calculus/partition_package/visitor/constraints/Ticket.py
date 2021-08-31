from z3 import *
import time

startT = time.time()
s = Optimize()

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

def nonCheckQ(q):
    constraints = []
    for i1 in range(n):
        for i2 in range(n):
            constraints.append(q[i1][i2] == 0)
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
                alive.append(Implies(h[a] == 0, q[i2][a] <= 0))
                #alive.append(Implies(q[i2][a] >= 0, q[i2][a] <= h[a] - b[i1][a]))
            subconstr.append(And(sLe(q[i2], h), Not(nonCheck(q[i2])), And(alive)))
        constraints.append(Or(subconstr))
    result = And(constraints)
    return result

def cIntegrity(b, q):
    constraints = []
    for i1 in range(n):
        subconstr = []
        for i2 in range(n):
            alive = []
            for a in range(n):
                #alive.append(Implies(a < n-1, b[i1][a] < q[i2][a]))
                alive.append(And(a < n-1, b[i1][a] < q[i2][a]))
            #subconstr.append(Implies(Not(nonCheck(q[i2])), And(alive)))
            subconstr.append(Implies(Not(nonCheck(q[i2])), Or(alive)))
        constraints.append(And(subconstr))
    result = And(constraints)
    return result

def cIntegrityE(b, q):
    constraints = []
    for i1 in range(n):
        subconstr = []
        for i2 in range(n):
            alive = []
            for a in range(n):
                alive.append(b[i1][a] < q[i2][a])
            subconstr.append(Implies(Not(nonCheck(q[i2])), Or(alive)))
        constraints.append(And(subconstr))
    result = And(constraints)
    return result

def sIntegrity(b, q, h):
    constraints = []
    for i3 in range(n):
        for i1 in range(n):
            for i2 in range(n):
                for j in range(n):
                    constraints.append(Implies(And(Not(q[i1][j] == 0), Not(q[i2][j] == 0)), 
                                               ((q[i1][j] + q[i2][j] - h[j]) > b[i3][j])))
    result = And(constraints)
    return result

def lableLe(c1, c2, i1, i2, a1, a2):
    return And(cLe(c1, c2), bLe(i2, i1), bLe(a2, a1))

n = 3
principals = [7, 10, 1]
startC = [ True, True, True ]
startI = [[ 7, 10, 0], [ 0, 0, 0], [ 0, 0, 0] ]
startA = [[ 7, 10, 0], [ 0, 0, 0], [ 0, 0, 0] ]
botC = [ True, True, True ]
botI = [[ 7, 10, 0], [ 0, 0, 0], [ 0, 0, 0] ]
botA = [[ 7, 10, 0], [ 0, 0, 0], [ 0, 0, 0] ]
resultC = [ True, True, True ]
resultI = [[ 2, 3, 0], [ 0, 0, 0], [ 0, 0, 0] ]
resultA = [[ 2, 3, 0], [ 0, 0, 0], [ 0, 0, 0] ]
resH = [0, 0, 1]
resQ = [ [ Int("resQ_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
s.add([ And(0 <= resQ[i][j]) for i in range(n) for j in range(n) ])
s.add([ And(sLe(resQ[i], principals)) for i in range(n) ])
m0H = [ Int('m0H_%s' % i) for i in range(n) ] 
m0Q = [ [ Int('m0Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0conxtC = [ Bool('m0conxtC_%s' % i) for i in range(n) ]
m0conxtI = [ [ Int('m0conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0conxtA = [ [ Int('m0conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0priceC = [ Bool('m0priceC_%s' % i) for i in range(n) ]
m0priceI = [ [ Int('m0priceI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0priceA = [ [ Int('m0priceA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0range0 = [ And(0 <= m0conxtI[i][j], 0 <= m0conxtA[i][j], 0 <= m0Q[i][j], 0 <= m0priceI[i][j], 0 <= m0priceA[i][j]) for i in range(n) for j in range(n) ]
s.add(m0range0)
m0range1 = [And(sLe(m0conxtI[i], principals), sLe(m0conxtA[i], principals), sLe(m0Q[i], principals), sLe(m0priceI[i], principals), sLe(m0priceA[i], principals)) for i in range(n)]
s.add(m0range1)
m0range2 = [And(0 <= m0H[i]) for i in range(n)]
s.add(m0range2)
s.add(sLe(m0H, principals))
s.add(Not(nonCheck(m0H)))
s.add(Not(nonCheckQ(m0Q)))
m1H = [ Int('m1H_%s' % i) for i in range(n) ] 
m1Q = [ [ Int('m1Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1conxtC = [ Bool('m1conxtC_%s' % i) for i in range(n) ]
m1conxtI = [ [ Int('m1conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1conxtA = [ [ Int('m1conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1priceC = [ Bool('m1priceC_%s' % i) for i in range(n) ]
m1priceI = [ [ Int('m1priceI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1priceA = [ [ Int('m1priceA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1numC = [ Bool('m1numC_%s' % i) for i in range(n) ]
m1numI = [ [ Int('m1numI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1numA = [ [ Int('m1numA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1range0 = [ And(0 <= m1conxtI[i][j], 0 <= m1conxtA[i][j], 0 <= m1Q[i][j], 0 <= m1priceI[i][j], 0 <= m1priceA[i][j], 0 <= m1numI[i][j], 0 <= m1numA[i][j]) for i in range(n) for j in range(n) ]
s.add(m1range0)
m1range1 = [And(sLe(m1conxtI[i], principals), sLe(m1conxtA[i], principals), sLe(m1Q[i], principals), sLe(m1priceI[i], principals), sLe(m1priceA[i], principals), sLe(m1numI[i], principals), sLe(m1numA[i], principals)) for i in range(n)]
s.add(m1range1)
m1range2 = [And(0 <= m1H[i]) for i in range(n)]
s.add(m1range2)
s.add(sLe(m1H, principals))
s.add(Not(nonCheck(m1H)))
s.add(Not(nonCheckQ(m1Q)))
m2H = [ Int('m2H_%s' % i) for i in range(n) ] 
m2Q = [ [ Int('m2Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2conxtC = [ Bool('m2conxtC_%s' % i) for i in range(n) ]
m2conxtI = [ [ Int('m2conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2conxtA = [ [ Int('m2conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2balanceC = [ Bool('m2balanceC_%s' % i) for i in range(n) ]
m2balanceI = [ [ Int('m2balanceI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2balanceA = [ [ Int('m2balanceA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2priceC = [ Bool('m2priceC_%s' % i) for i in range(n) ]
m2priceI = [ [ Int('m2priceI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2priceA = [ [ Int('m2priceA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2numC = [ Bool('m2numC_%s' % i) for i in range(n) ]
m2numI = [ [ Int('m2numI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2numA = [ [ Int('m2numA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2cashbackC = [ Bool('m2cashbackC_%s' % i) for i in range(n) ]
m2cashbackI = [ [ Int('m2cashbackI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2cashbackA = [ [ Int('m2cashbackA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2range0 = [ And(0 <= m2conxtI[i][j], 0 <= m2conxtA[i][j], 0 <= m2Q[i][j], 0 <= m2balanceI[i][j], 0 <= m2balanceA[i][j], 0 <= m2priceI[i][j], 0 <= m2priceA[i][j], 0 <= m2numI[i][j], 0 <= m2numA[i][j], 0 <= m2cashbackI[i][j], 0 <= m2cashbackA[i][j]) for i in range(n) for j in range(n) ]
s.add(m2range0)
m2range1 = [And(sLe(m2conxtI[i], principals), sLe(m2conxtA[i], principals), sLe(m2Q[i], principals), sLe(m2balanceI[i], principals), sLe(m2balanceA[i], principals), sLe(m2priceI[i], principals), sLe(m2priceA[i], principals), sLe(m2numI[i], principals), sLe(m2numA[i], principals), sLe(m2cashbackI[i], principals), sLe(m2cashbackA[i], principals)) for i in range(n)]
s.add(m2range1)
m2range2 = [And(0 <= m2H[i]) for i in range(n)]
s.add(m2range2)
s.add(sLe(m2H, principals))
s.add(Not(nonCheck(m2H)))
s.add(Not(nonCheckQ(m2Q)))
m3H = [ Int('m3H_%s' % i) for i in range(n) ] 
m3Q = [ [ Int('m3Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3conxtC = [ Bool('m3conxtC_%s' % i) for i in range(n) ]
m3conxtI = [ [ Int('m3conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3conxtA = [ [ Int('m3conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3priceC = [ Bool('m3priceC_%s' % i) for i in range(n) ]
m3priceI = [ [ Int('m3priceI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3priceA = [ [ Int('m3priceA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3numC = [ Bool('m3numC_%s' % i) for i in range(n) ]
m3numI = [ [ Int('m3numI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3numA = [ [ Int('m3numA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3IDC = [ Bool('m3IDC_%s' % i) for i in range(n) ]
m3IDI = [ [ Int('m3IDI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3IDA = [ [ Int('m3IDA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3cashbackC = [ Bool('m3cashbackC_%s' % i) for i in range(n) ]
m3cashbackI = [ [ Int('m3cashbackI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3cashbackA = [ [ Int('m3cashbackA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3range0 = [ And(0 <= m3conxtI[i][j], 0 <= m3conxtA[i][j], 0 <= m3Q[i][j], 0 <= m3priceI[i][j], 0 <= m3priceA[i][j], 0 <= m3numI[i][j], 0 <= m3numA[i][j], 0 <= m3IDI[i][j], 0 <= m3IDA[i][j], 0 <= m3cashbackI[i][j], 0 <= m3cashbackA[i][j]) for i in range(n) for j in range(n) ]
s.add(m3range0)
m3range1 = [And(sLe(m3conxtI[i], principals), sLe(m3conxtA[i], principals), sLe(m3Q[i], principals), sLe(m3priceI[i], principals), sLe(m3priceA[i], principals), sLe(m3numI[i], principals), sLe(m3numA[i], principals), sLe(m3IDI[i], principals), sLe(m3IDA[i], principals), sLe(m3cashbackI[i], principals), sLe(m3cashbackA[i], principals)) for i in range(n)]
s.add(m3range1)
m3range2 = [And(0 <= m3H[i]) for i in range(n)]
s.add(m3range2)
s.add(sLe(m3H, principals))
s.add(Not(nonCheck(m3H)))
s.add(Not(nonCheckQ(m3Q)))
m4H = [ Int('m4H_%s' % i) for i in range(n) ] 
m4Q = [ [ Int('m4Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4conxtC = [ Bool('m4conxtC_%s' % i) for i in range(n) ]
m4conxtI = [ [ Int('m4conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4conxtA = [ [ Int('m4conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4priceC = [ Bool('m4priceC_%s' % i) for i in range(n) ]
m4priceI = [ [ Int('m4priceI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4priceA = [ [ Int('m4priceA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4numC = [ Bool('m4numC_%s' % i) for i in range(n) ]
m4numI = [ [ Int('m4numI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4numA = [ [ Int('m4numA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4IDC = [ Bool('m4IDC_%s' % i) for i in range(n) ]
m4IDI = [ [ Int('m4IDI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4IDA = [ [ Int('m4IDA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4range0 = [ And(0 <= m4conxtI[i][j], 0 <= m4conxtA[i][j], 0 <= m4Q[i][j], 0 <= m4priceI[i][j], 0 <= m4priceA[i][j], 0 <= m4numI[i][j], 0 <= m4numA[i][j], 0 <= m4IDI[i][j], 0 <= m4IDA[i][j]) for i in range(n) for j in range(n) ]
s.add(m4range0)
m4range1 = [And(sLe(m4conxtI[i], principals), sLe(m4conxtA[i], principals), sLe(m4Q[i], principals), sLe(m4priceI[i], principals), sLe(m4priceA[i], principals), sLe(m4numI[i], principals), sLe(m4numA[i], principals), sLe(m4IDI[i], principals), sLe(m4IDA[i], principals)) for i in range(n)]
s.add(m4range1)
m4range2 = [And(0 <= m4H[i]) for i in range(n)]
s.add(m4range2)
s.add(sLe(m4H, principals))
s.add(Not(nonCheck(m4H)))
s.add(Not(nonCheckQ(m4Q)))
m5H = [ Int('m5H_%s' % i) for i in range(n) ] 
m5Q = [ [ Int('m5Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5conxtC = [ Bool('m5conxtC_%s' % i) for i in range(n) ]
m5conxtI = [ [ Int('m5conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5conxtA = [ [ Int('m5conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5priceC = [ Bool('m5priceC_%s' % i) for i in range(n) ]
m5priceI = [ [ Int('m5priceI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5priceA = [ [ Int('m5priceA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5numC = [ Bool('m5numC_%s' % i) for i in range(n) ]
m5numI = [ [ Int('m5numI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5numA = [ [ Int('m5numA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5range0 = [ And(0 <= m5conxtI[i][j], 0 <= m5conxtA[i][j], 0 <= m5Q[i][j], 0 <= m5priceI[i][j], 0 <= m5priceA[i][j], 0 <= m5numI[i][j], 0 <= m5numA[i][j]) for i in range(n) for j in range(n) ]
s.add(m5range0)
m5range1 = [And(sLe(m5conxtI[i], principals), sLe(m5conxtA[i], principals), sLe(m5Q[i], principals), sLe(m5priceI[i], principals), sLe(m5priceA[i], principals), sLe(m5numI[i], principals), sLe(m5numA[i], principals)) for i in range(n)]
s.add(m5range1)
m5range2 = [And(0 <= m5H[i]) for i in range(n)]
s.add(m5range2)
s.add(sLe(m5H, principals))
s.add(Not(nonCheck(m5H)))
s.add(Not(nonCheckQ(m5Q)))
m6H = [ Int('m6H_%s' % i) for i in range(n) ] 
m6Q = [ [ Int('m6Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6conxtC = [ Bool('m6conxtC_%s' % i) for i in range(n) ]
m6conxtI = [ [ Int('m6conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6conxtA = [ [ Int('m6conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6scheduleC = [ Bool('m6scheduleC_%s' % i) for i in range(n) ]
m6scheduleI = [ [ Int('m6scheduleI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6scheduleA = [ [ Int('m6scheduleA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6priceC = [ Bool('m6priceC_%s' % i) for i in range(n) ]
m6priceI = [ [ Int('m6priceI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6priceA = [ [ Int('m6priceA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6numC = [ Bool('m6numC_%s' % i) for i in range(n) ]
m6numI = [ [ Int('m6numI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6numA = [ [ Int('m6numA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6range0 = [ And(0 <= m6conxtI[i][j], 0 <= m6conxtA[i][j], 0 <= m6Q[i][j], 0 <= m6scheduleI[i][j], 0 <= m6scheduleA[i][j], 0 <= m6priceI[i][j], 0 <= m6priceA[i][j], 0 <= m6numI[i][j], 0 <= m6numA[i][j]) for i in range(n) for j in range(n) ]
s.add(m6range0)
m6range1 = [And(sLe(m6conxtI[i], principals), sLe(m6conxtA[i], principals), sLe(m6Q[i], principals), sLe(m6scheduleI[i], principals), sLe(m6scheduleA[i], principals), sLe(m6priceI[i], principals), sLe(m6priceA[i], principals), sLe(m6numI[i], principals), sLe(m6numA[i], principals)) for i in range(n)]
s.add(m6range1)
m6range2 = [And(0 <= m6H[i]) for i in range(n)]
s.add(m6range2)
s.add(sLe(m6H, principals))
s.add(Not(nonCheck(m6H)))
s.add(Not(nonCheckQ(m6Q)))
m7H = [ Int('m7H_%s' % i) for i in range(n) ] 
m7Q = [ [ Int('m7Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m7conxtC = [ Bool('m7conxtC_%s' % i) for i in range(n) ]
m7conxtI = [ [ Int('m7conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m7conxtA = [ [ Int('m7conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m7scheduleC = [ Bool('m7scheduleC_%s' % i) for i in range(n) ]
m7scheduleI = [ [ Int('m7scheduleI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m7scheduleA = [ [ Int('m7scheduleA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m7numC = [ Bool('m7numC_%s' % i) for i in range(n) ]
m7numI = [ [ Int('m7numI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m7numA = [ [ Int('m7numA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m7range0 = [ And(0 <= m7conxtI[i][j], 0 <= m7conxtA[i][j], 0 <= m7Q[i][j], 0 <= m7scheduleI[i][j], 0 <= m7scheduleA[i][j], 0 <= m7numI[i][j], 0 <= m7numA[i][j]) for i in range(n) for j in range(n) ]
s.add(m7range0)
m7range1 = [And(sLe(m7conxtI[i], principals), sLe(m7conxtA[i], principals), sLe(m7Q[i], principals), sLe(m7scheduleI[i], principals), sLe(m7scheduleA[i], principals), sLe(m7numI[i], principals), sLe(m7numA[i], principals)) for i in range(n)]
s.add(m7range1)
m7range2 = [And(0 <= m7H[i]) for i in range(n)]
s.add(m7range2)
s.add(sLe(m7H, principals))
s.add(Not(nonCheck(m7H)))
s.add(Not(nonCheckQ(m7Q)))
m8H = [ Int('m8H_%s' % i) for i in range(n) ] 
m8Q = [ [ Int('m8Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m8conxtC = [ Bool('m8conxtC_%s' % i) for i in range(n) ]
m8conxtI = [ [ Int('m8conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m8conxtA = [ [ Int('m8conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m8numC = [ Bool('m8numC_%s' % i) for i in range(n) ]
m8numI = [ [ Int('m8numI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m8numA = [ [ Int('m8numA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m8range0 = [ And(0 <= m8conxtI[i][j], 0 <= m8conxtA[i][j], 0 <= m8Q[i][j], 0 <= m8numI[i][j], 0 <= m8numA[i][j]) for i in range(n) for j in range(n) ]
s.add(m8range0)
m8range1 = [And(sLe(m8conxtI[i], principals), sLe(m8conxtA[i], principals), sLe(m8Q[i], principals), sLe(m8numI[i], principals), sLe(m8numA[i], principals)) for i in range(n)]
s.add(m8range1)
m8range2 = [And(0 <= m8H[i]) for i in range(n)]
s.add(m8range2)
s.add(sLe(m8H, principals))
s.add(Not(nonCheck(m8H)))
s.add(Not(nonCheckQ(m8Q)))
m9H = [ Int('m9H_%s' % i) for i in range(n) ] 
m9Q = [ [ Int('m9Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m9conxtC = [ Bool('m9conxtC_%s' % i) for i in range(n) ]
m9conxtI = [ [ Int('m9conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m9conxtA = [ [ Int('m9conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m9botC = m9conxtC
m9botI = m9conxtI
m9botA = m9conxtA
m9range0 = [ And(0 <= m9conxtI[i][j], 0 <= m9conxtA[i][j], 0 <= m9Q[i][j], 0 <= m9botI[i][j], 0 <= m9botA[i][j]) for i in range(n) for j in range(n) ]
s.add(m9range0)
m9range1 = [And(sLe(m9conxtI[i], principals), sLe(m9conxtA[i], principals), sLe(m9Q[i], principals), sLe(m9botI[i], principals), sLe(m9botA[i], principals)) for i in range(n)]
s.add(m9range1)
m9range2 = [And(0 <= m9H[i]) for i in range(n)]
s.add(m9range2)
s.add(sLe(m9H, principals))
s.add(Not(nonCheck(m9H)))
s.add(Not(nonCheckQ(m9Q)))
bankqs = [ [ Int("bankqs_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
bankqc = [ [ Int("bankqc_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
bankOH = [ Int('bankOH_%s' % i) for i in range(n) ] 
bankgetBalance2input0C = [ Bool('bankgetBalance2input0C_%s' % i) for i in range(n) ]
bankgetBalance2input0I = [ [ Int("bankgetBalance2input0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
bankgetBalance2input0A = [ [ Int("bankgetBalance2input0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
bankgetBalance2outputC = [ True, True, True ]
bankgetBalance2outputI = [ [ Int("bankgetBalance2outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
bankgetBalance2outputA = [ [ Int("bankgetBalance2outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
bankgetBalance1input0C = [ Bool('bankgetBalance1input0C_%s' % i) for i in range(n) ]
bankgetBalance1input0I = [ [ Int("bankgetBalance1input0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
bankgetBalance1input0A = [ [ Int("bankgetBalance1input0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
bankgetBalance1outputC = [ False, True, True ]
bankgetBalance1outputI = [ [ Int("bankgetBalance1outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
bankgetBalance1outputA = [ [ Int("bankgetBalance1outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
bankdecBalanceinput0C = [ Bool('bankdecBalanceinput0C_%s' % i) for i in range(n) ]
bankdecBalanceinput0I = [ [ Int("bankdecBalanceinput0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
bankdecBalanceinput0A = [ [ Int("bankdecBalanceinput0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
bankdecBalanceoutputC = [ Bool('bankdecBalanceoutputC_%s' % i) for i in range(n) ]
bankdecBalanceoutputI = [ [ Int("bankdecBalanceoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
bankdecBalanceoutputA = [ [ Int("bankdecBalanceoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
bankrange0 = [ And(0 <= bankqs[i][j], 0 <= bankqc[i][j], 0 <= bankgetBalance2outputI[i][j], 0 <= bankgetBalance2outputA[i][j], 0 <= bankgetBalance2input0I[i][j], 0 <= bankgetBalance2input0A[i][j], 0 <= bankgetBalance1outputI[i][j], 0 <= bankgetBalance1outputA[i][j], 0 <= bankgetBalance1input0I[i][j], 0 <= bankgetBalance1input0A[i][j], 0 <= bankdecBalanceoutputI[i][j], 0 <= bankdecBalanceoutputA[i][j], 0 <= bankdecBalanceinput0I[i][j], 0 <= bankdecBalanceinput0A[i][j]) for i in range(n) for j in range(n) ]
s.add(bankrange0)
bankrange1 = [And(sLe(bankqs[i], principals), sLe(bankqc[i], principals), sLe(bankgetBalance2outputI[i], principals), sLe(bankgetBalance2outputA[i], principals), sLe(bankgetBalance2input0I[i], principals), sLe(bankgetBalance2input0A[i], principals), sLe(bankgetBalance1outputI[i], principals), sLe(bankgetBalance1outputA[i], principals), sLe(bankgetBalance1input0I[i], principals), sLe(bankgetBalance1input0A[i], principals), sLe(bankdecBalanceoutputI[i], principals), sLe(bankdecBalanceoutputA[i], principals), sLe(bankdecBalanceinput0I[i], principals), sLe(bankdecBalanceinput0A[i], principals)) for i in range(n)]
s.add(bankrange1)
bankrange2 = [And(0 <= bankOH[i]) for i in range(n)]
s.add(bankrange2)
bankrange3 = sLe(bankOH, principals)
s.add(bankrange3)
airlineqs = [ [ Int("airlineqs_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
airlineqc = [ [ Int("airlineqc_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
airlineOH = [ Int('airlineOH_%s' % i) for i in range(n) ] 
airlinegetPrice2input0C = [ Bool('airlinegetPrice2input0C_%s' % i) for i in range(n) ]
airlinegetPrice2input0I = [ [ Int("airlinegetPrice2input0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
airlinegetPrice2input0A = [ [ Int("airlinegetPrice2input0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
airlinegetPrice2outputC = [ True, True, True ]
airlinegetPrice2outputI = [ [ Int("airlinegetPrice2outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
airlinegetPrice2outputA = [ [ Int("airlinegetPrice2outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
airlinegetPrice1input0C = [ Bool('airlinegetPrice1input0C_%s' % i) for i in range(n) ]
airlinegetPrice1input0I = [ [ Int("airlinegetPrice1input0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
airlinegetPrice1input0A = [ [ Int("airlinegetPrice1input0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
airlinegetPrice1outputC = [ True, False, True ]
airlinegetPrice1outputI = [ [ Int("airlinegetPrice1outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
airlinegetPrice1outputA = [ [ Int("airlinegetPrice1outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
airlinedecSeatinput0C = [ Bool('airlinedecSeatinput0C_%s' % i) for i in range(n) ]
airlinedecSeatinput0I = [ [ Int("airlinedecSeatinput0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
airlinedecSeatinput0A = [ [ Int("airlinedecSeatinput0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
airlinedecSeatoutputC = [ Bool('airlinedecSeatoutputC_%s' % i) for i in range(n) ]
airlinedecSeatoutputI = [ [ Int("airlinedecSeatoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
airlinedecSeatoutputA = [ [ Int("airlinedecSeatoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
airlinerange0 = [ And(0 <= airlineqs[i][j], 0 <= airlineqc[i][j], 0 <= airlinegetPrice2outputI[i][j], 0 <= airlinegetPrice2outputA[i][j], 0 <= airlinegetPrice2input0I[i][j], 0 <= airlinegetPrice2input0A[i][j], 0 <= airlinegetPrice1outputI[i][j], 0 <= airlinegetPrice1outputA[i][j], 0 <= airlinegetPrice1input0I[i][j], 0 <= airlinegetPrice1input0A[i][j], 0 <= airlinedecSeatoutputI[i][j], 0 <= airlinedecSeatoutputA[i][j], 0 <= airlinedecSeatinput0I[i][j], 0 <= airlinedecSeatinput0A[i][j]) for i in range(n) for j in range(n) ]
s.add(airlinerange0)
airlinerange1 = [And(sLe(airlineqs[i], principals), sLe(airlineqc[i], principals), sLe(airlinegetPrice2outputI[i], principals), sLe(airlinegetPrice2outputA[i], principals), sLe(airlinegetPrice2input0I[i], principals), sLe(airlinegetPrice2input0A[i], principals), sLe(airlinegetPrice1outputI[i], principals), sLe(airlinegetPrice1outputA[i], principals), sLe(airlinegetPrice1input0I[i], principals), sLe(airlinegetPrice1input0A[i], principals), sLe(airlinedecSeatoutputI[i], principals), sLe(airlinedecSeatoutputA[i], principals), sLe(airlinedecSeatinput0I[i], principals), sLe(airlinedecSeatinput0A[i], principals)) for i in range(n)]
s.add(airlinerange1)
airlinerange2 = [And(0 <= airlineOH[i]) for i in range(n)]
s.add(airlinerange2)
airlinerange3 = sLe(airlineOH, principals)
s.add(airlinerange3)
customerqs = [ [ Int("customerqs_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customerqc = [ [ Int("customerqc_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customerOH = [ Int('customerOH_%s' % i) for i in range(n) ] 
customerupdateInfoinput0C = [ Bool('customerupdateInfoinput0C_%s' % i) for i in range(n) ]
customerupdateInfoinput0I = [ [ Int("customerupdateInfoinput0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customerupdateInfoinput0A = [ [ Int("customerupdateInfoinput0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customerupdateInfoinput1C = [ Bool('customerupdateInfoinput1C_%s' % i) for i in range(n) ]
customerupdateInfoinput1I = [ [ Int("customerupdateInfoinput1I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customerupdateInfoinput1A = [ [ Int("customerupdateInfoinput1A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customerupdateInfooutputC = [ Bool('customerupdateInfooutputC_%s' % i) for i in range(n) ]
customerupdateInfooutputI = [ [ Int("customerupdateInfooutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customerupdateInfooutputA = [ [ Int("customerupdateInfooutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customerticketNumbotC = [ Bool('customerticketNumbotC_%s' % i) for i in range(n) ]
customerticketNumbotI = [ [ Int("customerticketNumbotI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customerticketNumbotA = [ [ Int("customerticketNumbotA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customerticketNumoutputC = [ Bool('customerticketNumoutputC_%s' % i) for i in range(n) ]
customerticketNumoutputI = [ [ Int("customerticketNumoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customerticketNumoutputA = [ [ Int("customerticketNumoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customergetIDbotC = [ Bool('customergetIDbotC_%s' % i) for i in range(n) ]
customergetIDbotI = [ [ Int("customergetIDbotI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customergetIDbotA = [ [ Int("customergetIDbotA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customergetIDoutputC = [ Bool('customergetIDoutputC_%s' % i) for i in range(n) ]
customergetIDoutputI = [ [ Int("customergetIDoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customergetIDoutputA = [ [ Int("customergetIDoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customerupdatePaymentinput0C = [ Bool('customerupdatePaymentinput0C_%s' % i) for i in range(n) ]
customerupdatePaymentinput0I = [ [ Int("customerupdatePaymentinput0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customerupdatePaymentinput0A = [ [ Int("customerupdatePaymentinput0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customerupdatePaymentinput1C = [ Bool('customerupdatePaymentinput1C_%s' % i) for i in range(n) ]
customerupdatePaymentinput1I = [ [ Int("customerupdatePaymentinput1I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customerupdatePaymentinput1A = [ [ Int("customerupdatePaymentinput1A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customerupdatePaymentoutputC = [ Bool('customerupdatePaymentoutputC_%s' % i) for i in range(n) ]
customerupdatePaymentoutputI = [ [ Int("customerupdatePaymentoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customerupdatePaymentoutputA = [ [ Int("customerupdatePaymentoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
customerrange0 = [ And(0 <= customerqs[i][j], 0 <= customerqc[i][j], 0 <= customerupdateInfooutputI[i][j], 0 <= customerupdateInfooutputA[i][j], 0 <= customerupdateInfoinput0I[i][j], 0 <= customerupdateInfoinput0A[i][j], 0 <= customerupdateInfoinput1I[i][j], 0 <= customerupdateInfoinput1A[i][j], 0 <= customerticketNumoutputI[i][j], 0 <= customerticketNumoutputA[i][j], 0 <= customerticketNumbotI[i][j], 0 <= customerticketNumbotA[i][j], 0 <= customergetIDoutputI[i][j], 0 <= customergetIDoutputA[i][j], 0 <= customergetIDbotI[i][j], 0 <= customergetIDbotA[i][j], 0 <= customerupdatePaymentoutputI[i][j], 0 <= customerupdatePaymentoutputA[i][j], 0 <= customerupdatePaymentinput0I[i][j], 0 <= customerupdatePaymentinput0A[i][j], 0 <= customerupdatePaymentinput1I[i][j], 0 <= customerupdatePaymentinput1A[i][j]) for i in range(n) for j in range(n) ]
s.add(customerrange0)
customerrange1 = [And(sLe(customerqs[i], principals), sLe(customerqc[i], principals), sLe(customerupdateInfooutputI[i], principals), sLe(customerupdateInfooutputA[i], principals), sLe(customerupdateInfoinput0I[i], principals), sLe(customerupdateInfoinput0A[i], principals), sLe(customerupdateInfoinput1I[i], principals), sLe(customerupdateInfoinput1A[i], principals), sLe(customerticketNumoutputI[i], principals), sLe(customerticketNumoutputA[i], principals), sLe(customerticketNumbotI[i], principals), sLe(customerticketNumbotA[i], principals), sLe(customergetIDoutputI[i], principals), sLe(customergetIDoutputA[i], principals), sLe(customergetIDbotI[i], principals), sLe(customergetIDbotA[i], principals), sLe(customerupdatePaymentoutputI[i], principals), sLe(customerupdatePaymentoutputA[i], principals), sLe(customerupdatePaymentinput0I[i], principals), sLe(customerupdatePaymentinput0A[i], principals), sLe(customerupdatePaymentinput1I[i], principals), sLe(customerupdatePaymentinput1A[i], principals)) for i in range(n)]
s.add(customerrange1)
customerrange2 = [And(0 <= customerOH[i]) for i in range(n)]
s.add(customerrange2)
customerrange3 = sLe(customerOH, principals)
s.add(customerrange3)
#FieldT: bank
s.add(cLeH(bankgetBalance2outputC, bankOH))
s.add(sIntegrity(bankgetBalance2outputI, bankqs, bankOH))
s.add(availabilityP(bankgetBalance2outputA, bankqs, bankOH))
s.add(cIntegrityE(bankgetBalance2input0I, bankqc))
s.add(lableLe(bankgetBalance2input0C, bankgetBalance2outputC, bankgetBalance2input0I, bankgetBalance2outputI, bankgetBalance2input0A, bankgetBalance2outputA))
s.add(cLeH(bankgetBalance1outputC, bankOH))
s.add(sIntegrity(bankgetBalance1outputI, bankqs, bankOH))
s.add(availabilityP(bankgetBalance1outputA, bankqs, bankOH))
s.add(cIntegrityE(bankgetBalance1input0I, bankqc))
s.add(lableLe(bankgetBalance1input0C, bankgetBalance1outputC, bankgetBalance1input0I, bankgetBalance1outputI, bankgetBalance1input0A, bankgetBalance1outputA))
s.add(cLeH(bankdecBalanceoutputC, bankOH))
s.add(sIntegrity(bankdecBalanceoutputI, bankqs, bankOH))
s.add(availabilityP(bankdecBalanceoutputA, bankqs, bankOH))
s.add(cIntegrityE(bankdecBalanceinput0I, bankqc))
s.add(lableLe(bankdecBalanceinput0C, bankdecBalanceoutputC, bankdecBalanceinput0I, bankdecBalanceoutputI, bankdecBalanceinput0A, bankdecBalanceoutputA))
#FieldT: airline
s.add(cLeH(airlinegetPrice2outputC, airlineOH))
s.add(sIntegrity(airlinegetPrice2outputI, airlineqs, airlineOH))
s.add(availabilityP(airlinegetPrice2outputA, airlineqs, airlineOH))
s.add(cIntegrityE(airlinegetPrice2input0I, airlineqc))
s.add(lableLe(airlinegetPrice2input0C, airlinegetPrice2outputC, airlinegetPrice2input0I, airlinegetPrice2outputI, airlinegetPrice2input0A, airlinegetPrice2outputA))
s.add(cLeH(airlinegetPrice1outputC, airlineOH))
s.add(sIntegrity(airlinegetPrice1outputI, airlineqs, airlineOH))
s.add(availabilityP(airlinegetPrice1outputA, airlineqs, airlineOH))
s.add(cIntegrityE(airlinegetPrice1input0I, airlineqc))
s.add(lableLe(airlinegetPrice1input0C, airlinegetPrice1outputC, airlinegetPrice1input0I, airlinegetPrice1outputI, airlinegetPrice1input0A, airlinegetPrice1outputA))
s.add(cLeH(airlinedecSeatoutputC, airlineOH))
s.add(sIntegrity(airlinedecSeatoutputI, airlineqs, airlineOH))
s.add(availabilityP(airlinedecSeatoutputA, airlineqs, airlineOH))
s.add(cIntegrityE(airlinedecSeatinput0I, airlineqc))
s.add(lableLe(airlinedecSeatinput0C, airlinedecSeatoutputC, airlinedecSeatinput0I, airlinedecSeatoutputI, airlinedecSeatinput0A, airlinedecSeatoutputA))
#FieldT: customer
s.add(cLeH(customerupdateInfooutputC, customerOH))
s.add(sIntegrity(customerupdateInfooutputI, customerqs, customerOH))
s.add(availabilityP(customerupdateInfooutputA, customerqs, customerOH))
s.add(cIntegrityE(customerupdateInfoinput0I, customerqc))
s.add(lableLe(customerupdateInfoinput0C, customerupdateInfooutputC, customerupdateInfoinput0I, customerupdateInfooutputI, customerupdateInfoinput0A, customerupdateInfooutputA))
s.add(cIntegrityE(customerupdateInfoinput1I, customerqc))
s.add(lableLe(customerupdateInfoinput1C, customerupdateInfooutputC, customerupdateInfoinput1I, customerupdateInfooutputI, customerupdateInfoinput1A, customerupdateInfooutputA))
s.add(cLeH(customerticketNumoutputC, customerOH))
s.add(sIntegrity(customerticketNumoutputI, customerqs, customerOH))
s.add(availabilityP(customerticketNumoutputA, customerqs, customerOH))
s.add(cIntegrityE(customerticketNumbotI, customerqc))
s.add(lableLe(customerticketNumbotC, customerticketNumoutputC, customerticketNumbotI, customerticketNumoutputI, customerticketNumbotA, customerticketNumoutputA))
s.add(cLeH(customergetIDoutputC, customerOH))
s.add(sIntegrity(customergetIDoutputI, customerqs, customerOH))
s.add(availabilityP(customergetIDoutputA, customerqs, customerOH))
s.add(cIntegrityE(customergetIDbotI, customerqc))
s.add(lableLe(customergetIDbotC, customergetIDoutputC, customergetIDbotI, customergetIDoutputI, customergetIDbotA, customergetIDoutputA))
s.add(cLeH(customerupdatePaymentoutputC, customerOH))
s.add(sIntegrity(customerupdatePaymentoutputI, customerqs, customerOH))
s.add(availabilityP(customerupdatePaymentoutputA, customerqs, customerOH))
s.add(cIntegrityE(customerupdatePaymentinput0I, customerqc))
s.add(lableLe(customerupdatePaymentinput0C, customerupdatePaymentoutputC, customerupdatePaymentinput0I, customerupdatePaymentoutputI, customerupdatePaymentinput0A, customerupdatePaymentoutputA))
s.add(cIntegrityE(customerupdatePaymentinput1I, customerqc))
s.add(lableLe(customerupdatePaymentinput1C, customerupdatePaymentoutputC, customerupdatePaymentinput1I, customerupdatePaymentoutputI, customerupdatePaymentinput1A, customerupdatePaymentoutputA))
#MethodT: m9
#ObjCallT: let num = customer.ticketNum() in let schedule = airline.getPrice1(num) in let price = airline.getPrice2(num) in let x20 = customer.updateInfo(schedule, price) in let ID = customer.getID() in let cashback = bank.getBalance1(ID) in let balance = bank.getBalance2(ID) in let x15 = customer.updatePayment(cashback, balance) in If ((price + balance)) then (let x11 = airline.decSeat(num) in let x9 = bank.decBalance(price) in this.ret(x9)) else (this.ret(0))
s.add(cLeH(customerticketNumoutputC, m9H))
s.add(availabilityP(customerticketNumbotA, customerqc, m9H))
#ThisCallT: this.m8(num)
s.add(cLe(m9conxtC, m8conxtC))
s.add(bLe(m8conxtI, m9conxtI))
s.add(bLe(m8conxtA, m9conxtA))
s.add(cLe(customerticketNumoutputC, m8numC))
s.add(cLe(botC, m8numC))
s.add(bLe(m8numI, customerticketNumoutputI))
s.add(bLe(m8numI, botI))
s.add(bLe(m8numA, customerticketNumoutputA))
s.add(bLe(m8numA, botA))
s.add(cLe(m9conxtC, m8numC))
s.add(bLe(m8numI, m9conxtI))
s.add(bLe(m8numA, m9conxtA))
s.add(availabilityP(m8numA, m8Q, m9H))
s.add(cIntegrityE(m9botI, m9Q))
s.add(cLeH(m9botC, m9H))
s.add(cLeH(m9conxtC, m9H))
#MethodT: m8
#ObjCallT: let schedule = airline.getPrice1(num) in let price = airline.getPrice2(num) in let x20 = customer.updateInfo(schedule, price) in let ID = customer.getID() in let cashback = bank.getBalance1(ID) in let balance = bank.getBalance2(ID) in let x15 = customer.updatePayment(cashback, balance) in If ((price + balance)) then (let x11 = airline.decSeat(num) in let x9 = bank.decBalance(price) in this.ret(x9)) else (this.ret(0))
s.add(cLeH(airlinegetPrice1outputC, m8H))
s.add(cLe(m8numC, airlinegetPrice1input0C))
s.add(cLe(botC, airlinegetPrice1input0C))
s.add(bLe(airlinegetPrice1input0I, m8numI))
s.add(bLe(airlinegetPrice1input0I, botI))
s.add(bLe(airlinegetPrice1input0A, m8numA))
s.add(bLe(airlinegetPrice1input0A, botA))
s.add(availabilityP(airlinegetPrice1input0A, airlineqc, m8H))
#ThisCallT: this.m7(schedule, num)
s.add(cLe(m8conxtC, m7conxtC))
s.add(bLe(m7conxtI, m8conxtI))
s.add(bLe(m7conxtA, m8conxtA))
s.add(cLe(airlinegetPrice1outputC, m7scheduleC))
s.add(cLe(botC, m7scheduleC))
s.add(bLe(m7scheduleI, airlinegetPrice1outputI))
s.add(bLe(m7scheduleI, botI))
s.add(bLe(m7scheduleA, airlinegetPrice1outputA))
s.add(bLe(m7scheduleA, botA))
s.add(cLe(m8conxtC, m7scheduleC))
s.add(bLe(m7scheduleI, m8conxtI))
s.add(bLe(m7scheduleA, m8conxtA))
s.add(availabilityP(m7scheduleA, m7Q, m8H))
s.add(cLe(m8numC, m7numC))
s.add(cLe(botC, m7numC))
s.add(cLe(botC, m7numC))
s.add(bLe(m7numI, m8numI))
s.add(bLe(m7numI, botI))
s.add(bLe(m7numI, botI))
s.add(bLe(m7numA, m8numA))
s.add(bLe(m7numA, botA))
s.add(bLe(m7numA, botA))
s.add(cLe(m8conxtC, m7numC))
s.add(bLe(m7numI, m8conxtI))
s.add(bLe(m7numA, m8conxtA))
s.add(availabilityP(m7numA, m7Q, m8H))
s.add(cIntegrityE(m8numI, m8Q))
s.add(cLeH(m8numC, m8H))
s.add(cLeH(m8conxtC, m8H))
#MethodT: m7
#ObjCallT: let price = airline.getPrice2(num) in let x20 = customer.updateInfo(schedule, price) in let ID = customer.getID() in let cashback = bank.getBalance1(ID) in let balance = bank.getBalance2(ID) in let x15 = customer.updatePayment(cashback, balance) in If ((price + balance)) then (let x11 = airline.decSeat(num) in let x9 = bank.decBalance(price) in this.ret(x9)) else (this.ret(0))
s.add(cLeH(airlinegetPrice2outputC, m7H))
s.add(cLe(m7numC, airlinegetPrice2input0C))
s.add(cLe(botC, airlinegetPrice2input0C))
s.add(bLe(airlinegetPrice2input0I, m7numI))
s.add(bLe(airlinegetPrice2input0I, botI))
s.add(bLe(airlinegetPrice2input0A, m7numA))
s.add(bLe(airlinegetPrice2input0A, botA))
s.add(availabilityP(airlinegetPrice2input0A, airlineqc, m7H))
#ThisCallT: this.m6(schedule, price, num)
s.add(cLe(m7conxtC, m6conxtC))
s.add(bLe(m6conxtI, m7conxtI))
s.add(bLe(m6conxtA, m7conxtA))
s.add(cLe(m7scheduleC, m6scheduleC))
s.add(cLe(botC, m6scheduleC))
s.add(bLe(m6scheduleI, m7scheduleI))
s.add(bLe(m6scheduleI, botI))
s.add(bLe(m6scheduleA, m7scheduleA))
s.add(bLe(m6scheduleA, botA))
s.add(cLe(m7conxtC, m6scheduleC))
s.add(bLe(m6scheduleI, m7conxtI))
s.add(bLe(m6scheduleA, m7conxtA))
s.add(availabilityP(m6scheduleA, m6Q, m7H))
s.add(cLe(airlinegetPrice2outputC, m6priceC))
s.add(cLe(botC, m6priceC))
s.add(bLe(m6priceI, airlinegetPrice2outputI))
s.add(bLe(m6priceI, botI))
s.add(bLe(m6priceA, airlinegetPrice2outputA))
s.add(bLe(m6priceA, botA))
s.add(cLe(m7conxtC, m6priceC))
s.add(bLe(m6priceI, m7conxtI))
s.add(bLe(m6priceA, m7conxtA))
s.add(availabilityP(m6priceA, m6Q, m7H))
s.add(cLe(m7numC, m6numC))
s.add(cLe(botC, m6numC))
s.add(cLe(botC, m6numC))
s.add(bLe(m6numI, m7numI))
s.add(bLe(m6numI, botI))
s.add(bLe(m6numI, botI))
s.add(bLe(m6numA, m7numA))
s.add(bLe(m6numA, botA))
s.add(bLe(m6numA, botA))
s.add(cLe(m7conxtC, m6numC))
s.add(bLe(m6numI, m7conxtI))
s.add(bLe(m6numA, m7conxtA))
s.add(availabilityP(m6numA, m6Q, m7H))
s.add(cIntegrityE(m7scheduleI, m7Q))
s.add(cIntegrityE(m7numI, m7Q))
s.add(cLeH(m7scheduleC, m7H))
s.add(cLeH(m7numC, m7H))
s.add(cLeH(m7conxtC, m7H))
#MethodT: m6
#ObjCallT: let x20 = customer.updateInfo(schedule, price) in let ID = customer.getID() in let cashback = bank.getBalance1(ID) in let balance = bank.getBalance2(ID) in let x15 = customer.updatePayment(cashback, balance) in If ((price + balance)) then (let x11 = airline.decSeat(num) in let x9 = bank.decBalance(price) in this.ret(x9)) else (this.ret(0))
s.add(cLeH(customerupdateInfooutputC, m6H))
s.add(cLe(m6scheduleC, customerupdateInfoinput0C))
s.add(cLe(botC, customerupdateInfoinput0C))
s.add(bLe(customerupdateInfoinput0I, m6scheduleI))
s.add(bLe(customerupdateInfoinput0I, botI))
s.add(bLe(customerupdateInfoinput0A, m6scheduleA))
s.add(bLe(customerupdateInfoinput0A, botA))
s.add(availabilityP(customerupdateInfoinput0A, customerqc, m6H))
s.add(cLe(m6priceC, customerupdateInfoinput1C))
s.add(cLe(botC, customerupdateInfoinput1C))
s.add(bLe(customerupdateInfoinput1I, m6priceI))
s.add(bLe(customerupdateInfoinput1I, botI))
s.add(bLe(customerupdateInfoinput1A, m6priceA))
s.add(bLe(customerupdateInfoinput1A, botA))
s.add(availabilityP(customerupdateInfoinput1A, customerqc, m6H))
#ThisCallT: this.m5(price, num)
s.add(cLe(m6conxtC, m5conxtC))
s.add(bLe(m5conxtI, m6conxtI))
s.add(bLe(m5conxtA, m6conxtA))
s.add(cLe(m6priceC, m5priceC))
s.add(cLe(botC, m5priceC))
s.add(cLe(botC, m5priceC))
s.add(bLe(m5priceI, m6priceI))
s.add(bLe(m5priceI, botI))
s.add(bLe(m5priceI, botI))
s.add(bLe(m5priceA, m6priceA))
s.add(bLe(m5priceA, botA))
s.add(bLe(m5priceA, botA))
s.add(cLe(m6conxtC, m5priceC))
s.add(bLe(m5priceI, m6conxtI))
s.add(bLe(m5priceA, m6conxtA))
s.add(availabilityP(m5priceA, m5Q, m6H))
s.add(cLe(m6numC, m5numC))
s.add(cLe(botC, m5numC))
s.add(bLe(m5numI, m6numI))
s.add(bLe(m5numI, botI))
s.add(bLe(m5numA, m6numA))
s.add(bLe(m5numA, botA))
s.add(cLe(m6conxtC, m5numC))
s.add(bLe(m5numI, m6conxtI))
s.add(bLe(m5numA, m6conxtA))
s.add(availabilityP(m5numA, m5Q, m6H))
s.add(cIntegrityE(m6scheduleI, m6Q))
s.add(cIntegrityE(m6priceI, m6Q))
s.add(cIntegrityE(m6numI, m6Q))
s.add(cLeH(m6scheduleC, m6H))
s.add(cLeH(m6priceC, m6H))
s.add(cLeH(m6numC, m6H))
s.add(cLeH(m6conxtC, m6H))
#MethodT: m5
#ObjCallT: let ID = customer.getID() in let cashback = bank.getBalance1(ID) in let balance = bank.getBalance2(ID) in let x15 = customer.updatePayment(cashback, balance) in If ((price + balance)) then (let x11 = airline.decSeat(num) in let x9 = bank.decBalance(price) in this.ret(x9)) else (this.ret(0))
s.add(cLeH(customergetIDoutputC, m5H))
s.add(availabilityP(customergetIDbotA, customerqc, m5H))
#ThisCallT: this.m4(price, num, ID)
s.add(cLe(m5conxtC, m4conxtC))
s.add(bLe(m4conxtI, m5conxtI))
s.add(bLe(m4conxtA, m5conxtA))
s.add(cLe(m5priceC, m4priceC))
s.add(cLe(botC, m4priceC))
s.add(bLe(m4priceI, m5priceI))
s.add(bLe(m4priceI, botI))
s.add(bLe(m4priceA, m5priceA))
s.add(bLe(m4priceA, botA))
s.add(cLe(m5conxtC, m4priceC))
s.add(bLe(m4priceI, m5conxtI))
s.add(bLe(m4priceA, m5conxtA))
s.add(availabilityP(m4priceA, m4Q, m5H))
s.add(cLe(m5numC, m4numC))
s.add(cLe(botC, m4numC))
s.add(bLe(m4numI, m5numI))
s.add(bLe(m4numI, botI))
s.add(bLe(m4numA, m5numA))
s.add(bLe(m4numA, botA))
s.add(cLe(m5conxtC, m4numC))
s.add(bLe(m4numI, m5conxtI))
s.add(bLe(m4numA, m5conxtA))
s.add(availabilityP(m4numA, m4Q, m5H))
s.add(cLe(customergetIDoutputC, m4IDC))
s.add(cLe(botC, m4IDC))
s.add(bLe(m4IDI, customergetIDoutputI))
s.add(bLe(m4IDI, botI))
s.add(bLe(m4IDA, customergetIDoutputA))
s.add(bLe(m4IDA, botA))
s.add(cLe(m5conxtC, m4IDC))
s.add(bLe(m4IDI, m5conxtI))
s.add(bLe(m4IDA, m5conxtA))
s.add(availabilityP(m4IDA, m4Q, m5H))
s.add(cIntegrityE(m5priceI, m5Q))
s.add(cIntegrityE(m5numI, m5Q))
s.add(cLeH(m5priceC, m5H))
s.add(cLeH(m5numC, m5H))
s.add(cLeH(m5conxtC, m5H))
#MethodT: m4
#ObjCallT: let cashback = bank.getBalance1(ID) in let balance = bank.getBalance2(ID) in let x15 = customer.updatePayment(cashback, balance) in If ((price + balance)) then (let x11 = airline.decSeat(num) in let x9 = bank.decBalance(price) in this.ret(x9)) else (this.ret(0))
s.add(cLeH(bankgetBalance1outputC, m4H))
s.add(cLe(m4IDC, bankgetBalance1input0C))
s.add(cLe(botC, bankgetBalance1input0C))
s.add(bLe(bankgetBalance1input0I, m4IDI))
s.add(bLe(bankgetBalance1input0I, botI))
s.add(bLe(bankgetBalance1input0A, m4IDA))
s.add(bLe(bankgetBalance1input0A, botA))
s.add(availabilityP(bankgetBalance1input0A, bankqc, m4H))
#ThisCallT: this.m3(price, num, ID, cashback)
s.add(cLe(m4conxtC, m3conxtC))
s.add(bLe(m3conxtI, m4conxtI))
s.add(bLe(m3conxtA, m4conxtA))
s.add(cLe(m4priceC, m3priceC))
s.add(cLe(botC, m3priceC))
s.add(bLe(m3priceI, m4priceI))
s.add(bLe(m3priceI, botI))
s.add(bLe(m3priceA, m4priceA))
s.add(bLe(m3priceA, botA))
s.add(cLe(m4conxtC, m3priceC))
s.add(bLe(m3priceI, m4conxtI))
s.add(bLe(m3priceA, m4conxtA))
s.add(availabilityP(m3priceA, m3Q, m4H))
s.add(cLe(m4numC, m3numC))
s.add(cLe(botC, m3numC))
s.add(bLe(m3numI, m4numI))
s.add(bLe(m3numI, botI))
s.add(bLe(m3numA, m4numA))
s.add(bLe(m3numA, botA))
s.add(cLe(m4conxtC, m3numC))
s.add(bLe(m3numI, m4conxtI))
s.add(bLe(m3numA, m4conxtA))
s.add(availabilityP(m3numA, m3Q, m4H))
s.add(cLe(m4IDC, m3IDC))
s.add(cLe(botC, m3IDC))
s.add(cLe(botC, m3IDC))
s.add(bLe(m3IDI, m4IDI))
s.add(bLe(m3IDI, botI))
s.add(bLe(m3IDI, botI))
s.add(bLe(m3IDA, m4IDA))
s.add(bLe(m3IDA, botA))
s.add(bLe(m3IDA, botA))
s.add(cLe(m4conxtC, m3IDC))
s.add(bLe(m3IDI, m4conxtI))
s.add(bLe(m3IDA, m4conxtA))
s.add(availabilityP(m3IDA, m3Q, m4H))
s.add(cLe(bankgetBalance1outputC, m3cashbackC))
s.add(cLe(botC, m3cashbackC))
s.add(bLe(m3cashbackI, bankgetBalance1outputI))
s.add(bLe(m3cashbackI, botI))
s.add(bLe(m3cashbackA, bankgetBalance1outputA))
s.add(bLe(m3cashbackA, botA))
s.add(cLe(m4conxtC, m3cashbackC))
s.add(bLe(m3cashbackI, m4conxtI))
s.add(bLe(m3cashbackA, m4conxtA))
s.add(availabilityP(m3cashbackA, m3Q, m4H))
s.add(cIntegrityE(m4priceI, m4Q))
s.add(cIntegrityE(m4numI, m4Q))
s.add(cIntegrityE(m4IDI, m4Q))
s.add(cLeH(m4priceC, m4H))
s.add(cLeH(m4numC, m4H))
s.add(cLeH(m4IDC, m4H))
s.add(cLeH(m4conxtC, m4H))
#MethodT: m3
#ObjCallT: let balance = bank.getBalance2(ID) in let x15 = customer.updatePayment(cashback, balance) in If ((price + balance)) then (let x11 = airline.decSeat(num) in let x9 = bank.decBalance(price) in this.ret(x9)) else (this.ret(0))
s.add(cLeH(bankgetBalance2outputC, m3H))
s.add(cLe(m3IDC, bankgetBalance2input0C))
s.add(cLe(botC, bankgetBalance2input0C))
s.add(bLe(bankgetBalance2input0I, m3IDI))
s.add(bLe(bankgetBalance2input0I, botI))
s.add(bLe(bankgetBalance2input0A, m3IDA))
s.add(bLe(bankgetBalance2input0A, botA))
s.add(availabilityP(bankgetBalance2input0A, bankqc, m3H))
#ThisCallT: this.m2(balance, price, num, cashback)
s.add(cLe(m3conxtC, m2conxtC))
s.add(bLe(m2conxtI, m3conxtI))
s.add(bLe(m2conxtA, m3conxtA))
s.add(cLe(bankgetBalance2outputC, m2balanceC))
s.add(cLe(botC, m2balanceC))
s.add(bLe(m2balanceI, bankgetBalance2outputI))
s.add(bLe(m2balanceI, botI))
s.add(bLe(m2balanceA, bankgetBalance2outputA))
s.add(bLe(m2balanceA, botA))
s.add(cLe(m3conxtC, m2balanceC))
s.add(bLe(m2balanceI, m3conxtI))
s.add(bLe(m2balanceA, m3conxtA))
s.add(availabilityP(m2balanceA, m2Q, m3H))
s.add(cLe(m3priceC, m2priceC))
s.add(cLe(botC, m2priceC))
s.add(bLe(m2priceI, m3priceI))
s.add(bLe(m2priceI, botI))
s.add(bLe(m2priceA, m3priceA))
s.add(bLe(m2priceA, botA))
s.add(cLe(m3conxtC, m2priceC))
s.add(bLe(m2priceI, m3conxtI))
s.add(bLe(m2priceA, m3conxtA))
s.add(availabilityP(m2priceA, m2Q, m3H))
s.add(cLe(m3numC, m2numC))
s.add(cLe(botC, m2numC))
s.add(bLe(m2numI, m3numI))
s.add(bLe(m2numI, botI))
s.add(bLe(m2numA, m3numA))
s.add(bLe(m2numA, botA))
s.add(cLe(m3conxtC, m2numC))
s.add(bLe(m2numI, m3conxtI))
s.add(bLe(m2numA, m3conxtA))
s.add(availabilityP(m2numA, m2Q, m3H))
s.add(cLe(m3cashbackC, m2cashbackC))
s.add(cLe(botC, m2cashbackC))
s.add(bLe(m2cashbackI, m3cashbackI))
s.add(bLe(m2cashbackI, botI))
s.add(bLe(m2cashbackA, m3cashbackA))
s.add(bLe(m2cashbackA, botA))
s.add(cLe(m3conxtC, m2cashbackC))
s.add(bLe(m2cashbackI, m3conxtI))
s.add(bLe(m2cashbackA, m3conxtA))
s.add(availabilityP(m2cashbackA, m2Q, m3H))
s.add(cIntegrityE(m3priceI, m3Q))
s.add(cIntegrityE(m3numI, m3Q))
s.add(cIntegrityE(m3IDI, m3Q))
s.add(cIntegrityE(m3cashbackI, m3Q))
s.add(cLeH(m3priceC, m3H))
s.add(cLeH(m3numC, m3H))
s.add(cLeH(m3IDC, m3H))
s.add(cLeH(m3cashbackC, m3H))
s.add(cLeH(m3conxtC, m3H))
#MethodT: m2
#ObjCallT: let x15 = customer.updatePayment(cashback, balance) in If ((price + balance)) then (let x11 = airline.decSeat(num) in let x9 = bank.decBalance(price) in this.ret(x9)) else (this.ret(0))
s.add(cLeH(customerupdatePaymentoutputC, m2H))
s.add(cLe(m2cashbackC, customerupdatePaymentinput0C))
s.add(cLe(botC, customerupdatePaymentinput0C))
s.add(bLe(customerupdatePaymentinput0I, m2cashbackI))
s.add(bLe(customerupdatePaymentinput0I, botI))
s.add(bLe(customerupdatePaymentinput0A, m2cashbackA))
s.add(bLe(customerupdatePaymentinput0A, botA))
s.add(availabilityP(customerupdatePaymentinput0A, customerqc, m2H))
s.add(cLe(m2balanceC, customerupdatePaymentinput1C))
s.add(cLe(botC, customerupdatePaymentinput1C))
s.add(bLe(customerupdatePaymentinput1I, m2balanceI))
s.add(bLe(customerupdatePaymentinput1I, botI))
s.add(bLe(customerupdatePaymentinput1A, m2balanceA))
s.add(bLe(customerupdatePaymentinput1A, botA))
s.add(availabilityP(customerupdatePaymentinput1A, customerqc, m2H))
#IfT: If ((price + balance)) then (this.m1(price, num)) else (this.ret(0))
#ThisCallT: this.m1(price, num)
s.add(cLe(m2conxtC, m1conxtC))
s.add(cLe(m2priceC, m1conxtC))
s.add(cLe(botC, m1conxtC))
s.add(cLe(m2balanceC, m1conxtC))
s.add(cLe(botC, m1conxtC))
s.add(cLe(botC, m1conxtC))
s.add(cLe(m2priceC, m1conxtC))
s.add(cLe(botC, m1conxtC))
s.add(cLe(m2balanceC, m1conxtC))
s.add(cLe(botC, m1conxtC))
s.add(cLe(botC, m1conxtC))
s.add(bLe(m1conxtI, m2conxtI))
s.add(bLe(m1conxtI, m2priceI))
s.add(bLe(m1conxtI, botI))
s.add(bLe(m1conxtI, m2balanceI))
s.add(bLe(m1conxtI, botI))
s.add(bLe(m1conxtI, botI))
s.add(bLe(m1conxtI, m2priceI))
s.add(bLe(m1conxtI, botI))
s.add(bLe(m1conxtI, m2balanceI))
s.add(bLe(m1conxtI, botI))
s.add(bLe(m1conxtI, botI))
s.add(bLe(m1conxtA, m2conxtA))
s.add(bLe(m1conxtA, m2priceA))
s.add(bLe(m1conxtA, botA))
s.add(bLe(m1conxtA, m2balanceA))
s.add(bLe(m1conxtA, botA))
s.add(bLe(m1conxtA, botA))
s.add(bLe(m1conxtA, m2priceA))
s.add(bLe(m1conxtA, botA))
s.add(bLe(m1conxtA, m2balanceA))
s.add(bLe(m1conxtA, botA))
s.add(bLe(m1conxtA, botA))
s.add(cLe(m2priceC, m1priceC))
s.add(cLe(botC, m1priceC))
s.add(cLe(botC, m1priceC))
s.add(bLe(m1priceI, m2priceI))
s.add(bLe(m1priceI, botI))
s.add(bLe(m1priceI, botI))
s.add(bLe(m1priceA, m2priceA))
s.add(bLe(m1priceA, botA))
s.add(bLe(m1priceA, botA))
s.add(cLe(m2conxtC, m1priceC))
s.add(cLe(m2priceC, m1priceC))
s.add(cLe(botC, m1priceC))
s.add(cLe(m2balanceC, m1priceC))
s.add(cLe(botC, m1priceC))
s.add(cLe(botC, m1priceC))
s.add(cLe(m2priceC, m1priceC))
s.add(cLe(botC, m1priceC))
s.add(cLe(m2balanceC, m1priceC))
s.add(cLe(botC, m1priceC))
s.add(cLe(botC, m1priceC))
s.add(bLe(m1priceI, m2conxtI))
s.add(bLe(m1priceI, m2priceI))
s.add(bLe(m1priceI, botI))
s.add(bLe(m1priceI, m2balanceI))
s.add(bLe(m1priceI, botI))
s.add(bLe(m1priceI, botI))
s.add(bLe(m1priceI, m2priceI))
s.add(bLe(m1priceI, botI))
s.add(bLe(m1priceI, m2balanceI))
s.add(bLe(m1priceI, botI))
s.add(bLe(m1priceI, botI))
s.add(bLe(m1priceA, m2conxtA))
s.add(bLe(m1priceA, m2priceA))
s.add(bLe(m1priceA, botA))
s.add(bLe(m1priceA, m2balanceA))
s.add(bLe(m1priceA, botA))
s.add(bLe(m1priceA, botA))
s.add(bLe(m1priceA, m2priceA))
s.add(bLe(m1priceA, botA))
s.add(bLe(m1priceA, m2balanceA))
s.add(bLe(m1priceA, botA))
s.add(bLe(m1priceA, botA))
s.add(availabilityP(m1priceA, m1Q, m2H))
s.add(cLe(m2numC, m1numC))
s.add(cLe(botC, m1numC))
s.add(bLe(m1numI, m2numI))
s.add(bLe(m1numI, botI))
s.add(bLe(m1numA, m2numA))
s.add(bLe(m1numA, botA))
s.add(cLe(m2conxtC, m1numC))
s.add(cLe(m2priceC, m1numC))
s.add(cLe(botC, m1numC))
s.add(cLe(m2balanceC, m1numC))
s.add(cLe(botC, m1numC))
s.add(cLe(botC, m1numC))
s.add(cLe(m2priceC, m1numC))
s.add(cLe(botC, m1numC))
s.add(cLe(m2balanceC, m1numC))
s.add(cLe(botC, m1numC))
s.add(cLe(botC, m1numC))
s.add(bLe(m1numI, m2conxtI))
s.add(bLe(m1numI, m2priceI))
s.add(bLe(m1numI, botI))
s.add(bLe(m1numI, m2balanceI))
s.add(bLe(m1numI, botI))
s.add(bLe(m1numI, botI))
s.add(bLe(m1numI, m2priceI))
s.add(bLe(m1numI, botI))
s.add(bLe(m1numI, m2balanceI))
s.add(bLe(m1numI, botI))
s.add(bLe(m1numI, botI))
s.add(bLe(m1numA, m2conxtA))
s.add(bLe(m1numA, m2priceA))
s.add(bLe(m1numA, botA))
s.add(bLe(m1numA, m2balanceA))
s.add(bLe(m1numA, botA))
s.add(bLe(m1numA, botA))
s.add(bLe(m1numA, m2priceA))
s.add(bLe(m1numA, botA))
s.add(bLe(m1numA, m2balanceA))
s.add(bLe(m1numA, botA))
s.add(bLe(m1numA, botA))
s.add(availabilityP(m1numA, m1Q, m2H))
#ThisCallT: this.ret(0)
s.add(cLe(m2conxtC, resultC))
s.add(cLe(m2priceC, resultC))
s.add(cLe(botC, resultC))
s.add(cLe(m2balanceC, resultC))
s.add(cLe(botC, resultC))
s.add(cLe(botC, resultC))
s.add(cLe(m2priceC, resultC))
s.add(cLe(botC, resultC))
s.add(cLe(m2balanceC, resultC))
s.add(cLe(botC, resultC))
s.add(cLe(botC, resultC))
s.add(bLe(resultI, m2conxtI))
s.add(bLe(resultI, m2priceI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, m2balanceI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, m2priceI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, m2balanceI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, botI))
s.add(bLe(resultA, m2conxtA))
s.add(bLe(resultA, m2priceA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, m2balanceA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, m2priceA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, m2balanceA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, botA))
s.add(cLe(botC, resultC))
s.add(bLe(resultI, botI))
s.add(bLe(resultA, botA))
s.add(cLe(m2conxtC, resultC))
s.add(cLe(m2priceC, resultC))
s.add(cLe(botC, resultC))
s.add(cLe(m2balanceC, resultC))
s.add(cLe(botC, resultC))
s.add(cLe(botC, resultC))
s.add(cLe(m2priceC, resultC))
s.add(cLe(botC, resultC))
s.add(cLe(m2balanceC, resultC))
s.add(cLe(botC, resultC))
s.add(cLe(botC, resultC))
s.add(bLe(resultI, m2conxtI))
s.add(bLe(resultI, m2priceI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, m2balanceI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, m2priceI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, m2balanceI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, botI))
s.add(bLe(resultA, m2conxtA))
s.add(bLe(resultA, m2priceA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, m2balanceA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, m2priceA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, m2balanceA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, botA))
s.add(availabilityP(resultA, resQ, m2H))
s.add(cIntegrityE(m2balanceI, m2Q))
s.add(cIntegrityE(m2priceI, m2Q))
s.add(cIntegrityE(m2numI, m2Q))
s.add(cIntegrityE(m2cashbackI, m2Q))
s.add(cLeH(m2balanceC, m2H))
s.add(cLeH(m2priceC, m2H))
s.add(cLeH(m2numC, m2H))
s.add(cLeH(m2cashbackC, m2H))
s.add(cLeH(m2conxtC, m2H))
#MethodT: m1
#ObjCallT: let x11 = airline.decSeat(num) in let x9 = bank.decBalance(price) in this.ret(x9)
s.add(cLeH(airlinedecSeatoutputC, m1H))
s.add(cLe(m1numC, airlinedecSeatinput0C))
s.add(cLe(botC, airlinedecSeatinput0C))
s.add(bLe(airlinedecSeatinput0I, m1numI))
s.add(bLe(airlinedecSeatinput0I, botI))
s.add(bLe(airlinedecSeatinput0A, m1numA))
s.add(bLe(airlinedecSeatinput0A, botA))
s.add(availabilityP(airlinedecSeatinput0A, airlineqc, m1H))
#ThisCallT: this.m0(price)
s.add(cLe(m1conxtC, m0conxtC))
s.add(bLe(m0conxtI, m1conxtI))
s.add(bLe(m0conxtA, m1conxtA))
s.add(cLe(m1priceC, m0priceC))
s.add(cLe(botC, m0priceC))
s.add(bLe(m0priceI, m1priceI))
s.add(bLe(m0priceI, botI))
s.add(bLe(m0priceA, m1priceA))
s.add(bLe(m0priceA, botA))
s.add(cLe(m1conxtC, m0priceC))
s.add(bLe(m0priceI, m1conxtI))
s.add(bLe(m0priceA, m1conxtA))
s.add(availabilityP(m0priceA, m0Q, m1H))
s.add(cIntegrityE(m1priceI, m1Q))
s.add(cIntegrityE(m1numI, m1Q))
s.add(cLeH(m1priceC, m1H))
s.add(cLeH(m1numC, m1H))
s.add(cLeH(m1conxtC, m1H))
#MethodT: m0
#ObjCallT: let x9 = bank.decBalance(price) in this.ret(x9)
s.add(cLeH(bankdecBalanceoutputC, m0H))
s.add(cLe(m0priceC, bankdecBalanceinput0C))
s.add(cLe(botC, bankdecBalanceinput0C))
s.add(bLe(bankdecBalanceinput0I, m0priceI))
s.add(bLe(bankdecBalanceinput0I, botI))
s.add(bLe(bankdecBalanceinput0A, m0priceA))
s.add(bLe(bankdecBalanceinput0A, botA))
s.add(availabilityP(bankdecBalanceinput0A, bankqc, m0H))
#ThisCallT: this.ret(x9)
s.add(cLe(m0conxtC, resultC))
s.add(bLe(resultI, m0conxtI))
s.add(bLe(resultA, m0conxtA))
s.add(cLe(bankdecBalanceoutputC, resultC))
s.add(cLe(botC, resultC))
s.add(bLe(resultI, bankdecBalanceoutputI))
s.add(bLe(resultI, botI))
s.add(bLe(resultA, bankdecBalanceoutputA))
s.add(bLe(resultA, botA))
s.add(cLe(m0conxtC, resultC))
s.add(bLe(resultI, m0conxtI))
s.add(bLe(resultA, m0conxtA))
s.add(availabilityP(resultA, resQ, m0H))
s.add(cIntegrityE(m0priceI, m0Q))
s.add(cLeH(m0priceC, m0H))
s.add(cLeH(m0conxtC, m0H))
#MethodT: ret
s.add(cLeH(resultC, resH))
s.add(cIntegrityE(resultI, resQ))
s.add(cLe(startC, m9conxtC))
s.add(bLe(m9conxtI, startI))
s.add(bLe(m9conxtA, startA))
s.add(availabilityP(m9botA, m9Q, resH))
s.add(cLe(startC, m9botC))
s.add(bLe(m9botI, startI))
s.add(bLe(m9botA, startA))
print("n = 3")
print("principals = [7, 10, 1]")
weight = [1, 1, 18]

s.minimize(sum(m0H[i] * weight[i] for i in range(n)) + sum(m1H[i] * weight[i] for i in range(n)) + sum(m2H[i] * weight[i] for i in range(n)) + sum(m3H[i] * weight[i] for i in range(n)) + sum(m4H[i] * weight[i] for i in range(n)) + sum(m5H[i] * weight[i] for i in range(n)) + sum(m6H[i] * weight[i] for i in range(n)) + sum(m7H[i] * weight[i] for i in range(n)) + sum(m8H[i] * weight[i] for i in range(n)) + sum(m9H[i] * weight[i] for i in range(n)) + sum(bankOH[i] * weight[i] for i in range(n)) + sum(airlineOH[i] * weight[i] for i in range(n)) + sum(customerOH[i] * weight[i] for i in range(n)) + sum(bankqs[0][i] * weight[i] for i in range(n)) + sum(bankqs[1][i] * weight[i] for i in range(n)) + sum(bankqs[2][i] * weight[i] for i in range(n)) + sum(airlineqs[0][i] * weight[i] for i in range(n)) + sum(airlineqs[1][i] * weight[i] for i in range(n)) + sum(airlineqs[2][i] * weight[i] for i in range(n)) + sum(customerqs[0][i] * weight[i] for i in range(n)) + sum(customerqs[1][i] * weight[i] for i in range(n)) + sum(customerqs[2][i] * weight[i] for i in range(n)) + sum(bankqc[0][i] * weight[i] for i in range(n)) + sum(bankqc[1][i] * weight[i] for i in range(n)) + sum(bankqc[2][i] * weight[i] for i in range(n)) + sum(airlineqc[0][i] * weight[i] for i in range(n)) + sum(airlineqc[1][i] * weight[i] for i in range(n)) + sum(airlineqc[2][i] * weight[i] for i in range(n)) + sum(customerqc[0][i] * weight[i] for i in range(n)) + sum(customerqc[1][i] * weight[i] for i in range(n)) + sum(customerqc[2][i] * weight[i] for i in range(n)) + sum(resQ[0]) + sum(resQ[1]) + sum(resQ[2]) + sum(m0Q[0]) + sum(m0Q[1]) + sum(m0Q[2]) + sum(m1Q[0]) + sum(m1Q[1]) + sum(m1Q[2]) + sum(m2Q[0]) + sum(m2Q[1]) + sum(m2Q[2]) + sum(m3Q[0]) + sum(m3Q[1]) + sum(m3Q[2]) + sum(m4Q[0]) + sum(m4Q[1]) + sum(m4Q[2]) + sum(m5Q[0]) + sum(m5Q[1]) + sum(m5Q[2]) + sum(m6Q[0]) + sum(m6Q[1]) + sum(m6Q[2]) + sum(m7Q[0]) + sum(m7Q[1]) + sum(m7Q[2]) + sum(m8Q[0]) + sum(m8Q[1]) + sum(m8Q[2]) + sum(m9Q[0]) + sum(m9Q[1]) + sum(m9Q[2]))
print(s.check())
m = s.model()
print("resH:", resH)
print("m0H:", [m[hInfo].as_long() for hInfo in m0H])
print("m1H:", [m[hInfo].as_long() for hInfo in m1H])
print("m2H:", [m[hInfo].as_long() for hInfo in m2H])
print("m3H:", [m[hInfo].as_long() for hInfo in m3H])
print("m4H:", [m[hInfo].as_long() for hInfo in m4H])
print("m5H:", [m[hInfo].as_long() for hInfo in m5H])
print("m6H:", [m[hInfo].as_long() for hInfo in m6H])
print("m7H:", [m[hInfo].as_long() for hInfo in m7H])
print("m8H:", [m[hInfo].as_long() for hInfo in m8H])
print("m9H:", [m[hInfo].as_long() for hInfo in m9H])
print("resQ:", [m[e].as_long() for qs in resQ for e in qs])
print("m0Q:", [m[e].as_long() for qs in m0Q for e in qs])
print("m1Q:", [m[e].as_long() for qs in m1Q for e in qs])
print("m2Q:", [m[e].as_long() for qs in m2Q for e in qs])
print("m3Q:", [m[e].as_long() for qs in m3Q for e in qs])
print("m4Q:", [m[e].as_long() for qs in m4Q for e in qs])
print("m5Q:", [m[e].as_long() for qs in m5Q for e in qs])
print("m6Q:", [m[e].as_long() for qs in m6Q for e in qs])
print("m7Q:", [m[e].as_long() for qs in m7Q for e in qs])
print("m8Q:", [m[e].as_long() for qs in m8Q for e in qs])
print("m9Q:", [m[e].as_long() for qs in m9Q for e in qs])
print("bankqs:", [m[e].as_long() for qs in bankqs for e in qs])
print("airlineqs:", [m[e].as_long() for qs in airlineqs for e in qs])
print("customerqs:", [m[e].as_long() for qs in customerqs for e in qs])
print("bankqc:", [m[e].as_long() for qs in bankqc for e in qs])
print("airlineqc:", [m[e].as_long() for qs in airlineqc for e in qs])
print("customerqc:", [m[e].as_long() for qs in customerqc for e in qs])
print("bankOH:", [m[hInfo].as_long() for hInfo in bankOH])
print("airlineOH:", [m[hInfo].as_long() for hInfo in airlineOH])
print("customerOH:", [m[hInfo].as_long() for hInfo in customerOH])
endT = time.time() - startT
print(endT)
