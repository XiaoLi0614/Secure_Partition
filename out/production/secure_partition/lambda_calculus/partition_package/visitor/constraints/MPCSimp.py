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


n = 3
principals = [ 7, 4, 4]
startC = [ True, True, True ]
startI = [[ 7, 4, 4], [ 0, 0, 0], [ 0, 0, 0] ]
startA = [[ 7, 4, 4], [ 0, 0, 0], [ 0, 0, 0] ]
botC = [ True, True, True ]
botI = [[ 7, 4, 4], [ 0, 0, 0], [ 0, 0, 0] ]
botA = [[ 7, 4, 4], [ 0, 0, 0], [ 0, 0, 0] ]
resultC = [ True, True, True ]
resultI = [[ 2, 1, 1], [ 0, 0, 0], [ 0, 0, 0] ]
resultA = [[ 2, 1, 1], [ 0, 0, 0], [ 0, 0, 0] ]
resH = [ 7, 0, 0]
resQ = [ [ Int("resQ_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
s.add([ And(0 <= resQ[i][j]) for i in range(n) for j in range(n) ])
s.add([ And(sLe(resQ[i], principals)) for i in range(n) ])
m0H = [ Int('m0H_%s' % i) for i in range(n) ] 
m0Q = [ [ Int('m0Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0conxtC = [ Bool('m0conxtC_%s' % i) for i in range(n) ]
m0conxtI = [ [ Int('m0conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0conxtA = [ [ Int('m0conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0sum1C = [ Bool('m0sum1C_%s' % i) for i in range(n) ]
m0sum1I = [ [ Int('m0sum1I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0sum1A = [ [ Int('m0sum1A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0sum2C = [ Bool('m0sum2C_%s' % i) for i in range(n) ]
m0sum2I = [ [ Int('m0sum2I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0sum2A = [ [ Int('m0sum2A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0rp13C = [ Bool('m0rp13C_%s' % i) for i in range(n) ]
m0rp13I = [ [ Int('m0rp13I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0rp13A = [ [ Int('m0rp13A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0rp23C = [ Bool('m0rp23C_%s' % i) for i in range(n) ]
m0rp23I = [ [ Int('m0rp23I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0rp23A = [ [ Int('m0rp23A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0rp33C = [ Bool('m0rp33C_%s' % i) for i in range(n) ]
m0rp33I = [ [ Int('m0rp33I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0rp33A = [ [ Int('m0rp33A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0range0 = [ And(0 <= m0conxtI[i][j], 0 <= m0conxtA[i][j], 0 <= m0Q[i][j], 0 <= m0sum1I[i][j], 0 <= m0sum1A[i][j], 0 <= m0sum2I[i][j], 0 <= m0sum2A[i][j], 0 <= m0rp13I[i][j], 0 <= m0rp13A[i][j], 0 <= m0rp23I[i][j], 0 <= m0rp23A[i][j], 0 <= m0rp33I[i][j], 0 <= m0rp33A[i][j]) for i in range(n) for j in range(n) ]
s.add(m0range0)
m0range1 = [And(sLe(m0conxtI[i], principals), sLe(m0conxtA[i], principals), sLe(m0Q[i], principals), sLe(m0sum1I[i], principals), sLe(m0sum1A[i], principals), sLe(m0sum2I[i], principals), sLe(m0sum2A[i], principals), sLe(m0rp13I[i], principals), sLe(m0rp13A[i], principals), sLe(m0rp23I[i], principals), sLe(m0rp23A[i], principals), sLe(m0rp33I[i], principals), sLe(m0rp33A[i], principals)) for i in range(n)]
s.add(m0range1)
m0range2 = [And(0 <= m0H[i]) for i in range(n)]
s.add(m0range2)
s.add(sLe(m0H, principals))
s.add(Not(nonCheck(m0H)))
m1H = [ Int('m1H_%s' % i) for i in range(n) ] 
m1Q = [ [ Int('m1Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1conxtC = [ Bool('m1conxtC_%s' % i) for i in range(n) ]
m1conxtI = [ [ Int('m1conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1conxtA = [ [ Int('m1conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1sum1C = [ Bool('m1sum1C_%s' % i) for i in range(n) ]
m1sum1I = [ [ Int('m1sum1I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1sum1A = [ [ Int('m1sum1A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1sum2C = [ Bool('m1sum2C_%s' % i) for i in range(n) ]
m1sum2I = [ [ Int('m1sum2I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1sum2A = [ [ Int('m1sum2A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1rp13C = [ Bool('m1rp13C_%s' % i) for i in range(n) ]
m1rp13I = [ [ Int('m1rp13I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1rp13A = [ [ Int('m1rp13A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1rp23C = [ Bool('m1rp23C_%s' % i) for i in range(n) ]
m1rp23I = [ [ Int('m1rp23I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1rp23A = [ [ Int('m1rp23A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1range0 = [ And(0 <= m1conxtI[i][j], 0 <= m1conxtA[i][j], 0 <= m1Q[i][j], 0 <= m1sum1I[i][j], 0 <= m1sum1A[i][j], 0 <= m1sum2I[i][j], 0 <= m1sum2A[i][j], 0 <= m1rp13I[i][j], 0 <= m1rp13A[i][j], 0 <= m1rp23I[i][j], 0 <= m1rp23A[i][j]) for i in range(n) for j in range(n) ]
s.add(m1range0)
m1range1 = [And(sLe(m1conxtI[i], principals), sLe(m1conxtA[i], principals), sLe(m1Q[i], principals), sLe(m1sum1I[i], principals), sLe(m1sum1A[i], principals), sLe(m1sum2I[i], principals), sLe(m1sum2A[i], principals), sLe(m1rp13I[i], principals), sLe(m1rp13A[i], principals), sLe(m1rp23I[i], principals), sLe(m1rp23A[i], principals)) for i in range(n)]
s.add(m1range1)
m1range2 = [And(0 <= m1H[i]) for i in range(n)]
s.add(m1range2)
s.add(sLe(m1H, principals))
s.add(Not(nonCheck(m1H)))
m2H = [ Int('m2H_%s' % i) for i in range(n) ] 
m2Q = [ [ Int('m2Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2conxtC = [ Bool('m2conxtC_%s' % i) for i in range(n) ]
m2conxtI = [ [ Int('m2conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2conxtA = [ [ Int('m2conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2sum1C = [ Bool('m2sum1C_%s' % i) for i in range(n) ]
m2sum1I = [ [ Int('m2sum1I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2sum1A = [ [ Int('m2sum1A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2sum2C = [ Bool('m2sum2C_%s' % i) for i in range(n) ]
m2sum2I = [ [ Int('m2sum2I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2sum2A = [ [ Int('m2sum2A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2rp13C = [ Bool('m2rp13C_%s' % i) for i in range(n) ]
m2rp13I = [ [ Int('m2rp13I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2rp13A = [ [ Int('m2rp13A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2range0 = [ And(0 <= m2conxtI[i][j], 0 <= m2conxtA[i][j], 0 <= m2Q[i][j], 0 <= m2sum1I[i][j], 0 <= m2sum1A[i][j], 0 <= m2sum2I[i][j], 0 <= m2sum2A[i][j], 0 <= m2rp13I[i][j], 0 <= m2rp13A[i][j]) for i in range(n) for j in range(n) ]
s.add(m2range0)
m2range1 = [And(sLe(m2conxtI[i], principals), sLe(m2conxtA[i], principals), sLe(m2Q[i], principals), sLe(m2sum1I[i], principals), sLe(m2sum1A[i], principals), sLe(m2sum2I[i], principals), sLe(m2sum2A[i], principals), sLe(m2rp13I[i], principals), sLe(m2rp13A[i], principals)) for i in range(n)]
s.add(m2range1)
m2range2 = [And(0 <= m2H[i]) for i in range(n)]
s.add(m2range2)
s.add(sLe(m2H, principals))
s.add(Not(nonCheck(m2H)))
m3H = [ Int('m3H_%s' % i) for i in range(n) ] 
m3Q = [ [ Int('m3Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3conxtC = [ Bool('m3conxtC_%s' % i) for i in range(n) ]
m3conxtI = [ [ Int('m3conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3conxtA = [ [ Int('m3conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3sum1C = [ Bool('m3sum1C_%s' % i) for i in range(n) ]
m3sum1I = [ [ Int('m3sum1I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3sum1A = [ [ Int('m3sum1A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3sum2C = [ Bool('m3sum2C_%s' % i) for i in range(n) ]
m3sum2I = [ [ Int('m3sum2I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3sum2A = [ [ Int('m3sum2A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3range0 = [ And(0 <= m3conxtI[i][j], 0 <= m3conxtA[i][j], 0 <= m3Q[i][j], 0 <= m3sum1I[i][j], 0 <= m3sum1A[i][j], 0 <= m3sum2I[i][j], 0 <= m3sum2A[i][j]) for i in range(n) for j in range(n) ]
s.add(m3range0)
m3range1 = [And(sLe(m3conxtI[i], principals), sLe(m3conxtA[i], principals), sLe(m3Q[i], principals), sLe(m3sum1I[i], principals), sLe(m3sum1A[i], principals), sLe(m3sum2I[i], principals), sLe(m3sum2A[i], principals)) for i in range(n)]
s.add(m3range1)
m3range2 = [And(0 <= m3H[i]) for i in range(n)]
s.add(m3range2)
s.add(sLe(m3H, principals))
s.add(Not(nonCheck(m3H)))
m4H = [ Int('m4H_%s' % i) for i in range(n) ] 
m4Q = [ [ Int('m4Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4conxtC = [ Bool('m4conxtC_%s' % i) for i in range(n) ]
m4conxtI = [ [ Int('m4conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4conxtA = [ [ Int('m4conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4sum1C = [ Bool('m4sum1C_%s' % i) for i in range(n) ]
m4sum1I = [ [ Int('m4sum1I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4sum1A = [ [ Int('m4sum1A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4rp32C = [ Bool('m4rp32C_%s' % i) for i in range(n) ]
m4rp32I = [ [ Int('m4rp32I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4rp32A = [ [ Int('m4rp32A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4rp12C = [ Bool('m4rp12C_%s' % i) for i in range(n) ]
m4rp12I = [ [ Int('m4rp12I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4rp12A = [ [ Int('m4rp12A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4rp22C = [ Bool('m4rp22C_%s' % i) for i in range(n) ]
m4rp22I = [ [ Int('m4rp22I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4rp22A = [ [ Int('m4rp22A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4range0 = [ And(0 <= m4conxtI[i][j], 0 <= m4conxtA[i][j], 0 <= m4Q[i][j], 0 <= m4sum1I[i][j], 0 <= m4sum1A[i][j], 0 <= m4rp32I[i][j], 0 <= m4rp32A[i][j], 0 <= m4rp12I[i][j], 0 <= m4rp12A[i][j], 0 <= m4rp22I[i][j], 0 <= m4rp22A[i][j]) for i in range(n) for j in range(n) ]
s.add(m4range0)
m4range1 = [And(sLe(m4conxtI[i], principals), sLe(m4conxtA[i], principals), sLe(m4Q[i], principals), sLe(m4sum1I[i], principals), sLe(m4sum1A[i], principals), sLe(m4rp32I[i], principals), sLe(m4rp32A[i], principals), sLe(m4rp12I[i], principals), sLe(m4rp12A[i], principals), sLe(m4rp22I[i], principals), sLe(m4rp22A[i], principals)) for i in range(n)]
s.add(m4range1)
m4range2 = [And(0 <= m4H[i]) for i in range(n)]
s.add(m4range2)
s.add(sLe(m4H, principals))
s.add(Not(nonCheck(m4H)))
m5H = [ Int('m5H_%s' % i) for i in range(n) ] 
m5Q = [ [ Int('m5Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5conxtC = [ Bool('m5conxtC_%s' % i) for i in range(n) ]
m5conxtI = [ [ Int('m5conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5conxtA = [ [ Int('m5conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5sum1C = [ Bool('m5sum1C_%s' % i) for i in range(n) ]
m5sum1I = [ [ Int('m5sum1I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5sum1A = [ [ Int('m5sum1A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5rp12C = [ Bool('m5rp12C_%s' % i) for i in range(n) ]
m5rp12I = [ [ Int('m5rp12I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5rp12A = [ [ Int('m5rp12A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5rp22C = [ Bool('m5rp22C_%s' % i) for i in range(n) ]
m5rp22I = [ [ Int('m5rp22I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5rp22A = [ [ Int('m5rp22A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5range0 = [ And(0 <= m5conxtI[i][j], 0 <= m5conxtA[i][j], 0 <= m5Q[i][j], 0 <= m5sum1I[i][j], 0 <= m5sum1A[i][j], 0 <= m5rp12I[i][j], 0 <= m5rp12A[i][j], 0 <= m5rp22I[i][j], 0 <= m5rp22A[i][j]) for i in range(n) for j in range(n) ]
s.add(m5range0)
m5range1 = [And(sLe(m5conxtI[i], principals), sLe(m5conxtA[i], principals), sLe(m5Q[i], principals), sLe(m5sum1I[i], principals), sLe(m5sum1A[i], principals), sLe(m5rp12I[i], principals), sLe(m5rp12A[i], principals), sLe(m5rp22I[i], principals), sLe(m5rp22A[i], principals)) for i in range(n)]
s.add(m5range1)
m5range2 = [And(0 <= m5H[i]) for i in range(n)]
s.add(m5range2)
s.add(sLe(m5H, principals))
s.add(Not(nonCheck(m5H)))
m6H = [ Int('m6H_%s' % i) for i in range(n) ] 
m6Q = [ [ Int('m6Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6conxtC = [ Bool('m6conxtC_%s' % i) for i in range(n) ]
m6conxtI = [ [ Int('m6conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6conxtA = [ [ Int('m6conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6sum1C = [ Bool('m6sum1C_%s' % i) for i in range(n) ]
m6sum1I = [ [ Int('m6sum1I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6sum1A = [ [ Int('m6sum1A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6rp12C = [ Bool('m6rp12C_%s' % i) for i in range(n) ]
m6rp12I = [ [ Int('m6rp12I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6rp12A = [ [ Int('m6rp12A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6range0 = [ And(0 <= m6conxtI[i][j], 0 <= m6conxtA[i][j], 0 <= m6Q[i][j], 0 <= m6sum1I[i][j], 0 <= m6sum1A[i][j], 0 <= m6rp12I[i][j], 0 <= m6rp12A[i][j]) for i in range(n) for j in range(n) ]
s.add(m6range0)
m6range1 = [And(sLe(m6conxtI[i], principals), sLe(m6conxtA[i], principals), sLe(m6Q[i], principals), sLe(m6sum1I[i], principals), sLe(m6sum1A[i], principals), sLe(m6rp12I[i], principals), sLe(m6rp12A[i], principals)) for i in range(n)]
s.add(m6range1)
m6range2 = [And(0 <= m6H[i]) for i in range(n)]
s.add(m6range2)
s.add(sLe(m6H, principals))
s.add(Not(nonCheck(m6H)))
m7H = [ Int('m7H_%s' % i) for i in range(n) ] 
m7Q = [ [ Int('m7Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m7conxtC = [ Bool('m7conxtC_%s' % i) for i in range(n) ]
m7conxtI = [ [ Int('m7conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m7conxtA = [ [ Int('m7conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m7sum1C = [ Bool('m7sum1C_%s' % i) for i in range(n) ]
m7sum1I = [ [ Int('m7sum1I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m7sum1A = [ [ Int('m7sum1A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m7range0 = [ And(0 <= m7conxtI[i][j], 0 <= m7conxtA[i][j], 0 <= m7Q[i][j], 0 <= m7sum1I[i][j], 0 <= m7sum1A[i][j]) for i in range(n) for j in range(n) ]
s.add(m7range0)
m7range1 = [And(sLe(m7conxtI[i], principals), sLe(m7conxtA[i], principals), sLe(m7Q[i], principals), sLe(m7sum1I[i], principals), sLe(m7sum1A[i], principals)) for i in range(n)]
s.add(m7range1)
m7range2 = [And(0 <= m7H[i]) for i in range(n)]
s.add(m7range2)
s.add(sLe(m7H, principals))
s.add(Not(nonCheck(m7H)))
m8H = [ Int('m8H_%s' % i) for i in range(n) ] 
m8Q = [ [ Int('m8Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m8conxtC = [ Bool('m8conxtC_%s' % i) for i in range(n) ]
m8conxtI = [ [ Int('m8conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m8conxtA = [ [ Int('m8conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m8rp21C = [ Bool('m8rp21C_%s' % i) for i in range(n) ]
m8rp21I = [ [ Int('m8rp21I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m8rp21A = [ [ Int('m8rp21A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m8rp31C = [ Bool('m8rp31C_%s' % i) for i in range(n) ]
m8rp31I = [ [ Int('m8rp31I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m8rp31A = [ [ Int('m8rp31A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m8rp11C = [ Bool('m8rp11C_%s' % i) for i in range(n) ]
m8rp11I = [ [ Int('m8rp11I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m8rp11A = [ [ Int('m8rp11A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m8range0 = [ And(0 <= m8conxtI[i][j], 0 <= m8conxtA[i][j], 0 <= m8Q[i][j], 0 <= m8rp21I[i][j], 0 <= m8rp21A[i][j], 0 <= m8rp31I[i][j], 0 <= m8rp31A[i][j], 0 <= m8rp11I[i][j], 0 <= m8rp11A[i][j]) for i in range(n) for j in range(n) ]
s.add(m8range0)
m8range1 = [And(sLe(m8conxtI[i], principals), sLe(m8conxtA[i], principals), sLe(m8Q[i], principals), sLe(m8rp21I[i], principals), sLe(m8rp21A[i], principals), sLe(m8rp31I[i], principals), sLe(m8rp31A[i], principals), sLe(m8rp11I[i], principals), sLe(m8rp11A[i], principals)) for i in range(n)]
s.add(m8range1)
m8range2 = [And(0 <= m8H[i]) for i in range(n)]
s.add(m8range2)
s.add(sLe(m8H, principals))
s.add(Not(nonCheck(m8H)))
m9H = [ Int('m9H_%s' % i) for i in range(n) ] 
m9Q = [ [ Int('m9Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m9conxtC = [ Bool('m9conxtC_%s' % i) for i in range(n) ]
m9conxtI = [ [ Int('m9conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m9conxtA = [ [ Int('m9conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m9rp21C = [ Bool('m9rp21C_%s' % i) for i in range(n) ]
m9rp21I = [ [ Int('m9rp21I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m9rp21A = [ [ Int('m9rp21A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m9rp11C = [ Bool('m9rp11C_%s' % i) for i in range(n) ]
m9rp11I = [ [ Int('m9rp11I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m9rp11A = [ [ Int('m9rp11A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m9range0 = [ And(0 <= m9conxtI[i][j], 0 <= m9conxtA[i][j], 0 <= m9Q[i][j], 0 <= m9rp21I[i][j], 0 <= m9rp21A[i][j], 0 <= m9rp11I[i][j], 0 <= m9rp11A[i][j]) for i in range(n) for j in range(n) ]
s.add(m9range0)
m9range1 = [And(sLe(m9conxtI[i], principals), sLe(m9conxtA[i], principals), sLe(m9Q[i], principals), sLe(m9rp21I[i], principals), sLe(m9rp21A[i], principals), sLe(m9rp11I[i], principals), sLe(m9rp11A[i], principals)) for i in range(n)]
s.add(m9range1)
m9range2 = [And(0 <= m9H[i]) for i in range(n)]
s.add(m9range2)
s.add(sLe(m9H, principals))
s.add(Not(nonCheck(m9H)))
m10H = [ Int('m10H_%s' % i) for i in range(n) ] 
m10Q = [ [ Int('m10Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m10conxtC = [ Bool('m10conxtC_%s' % i) for i in range(n) ]
m10conxtI = [ [ Int('m10conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m10conxtA = [ [ Int('m10conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m10rp11C = [ Bool('m10rp11C_%s' % i) for i in range(n) ]
m10rp11I = [ [ Int('m10rp11I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m10rp11A = [ [ Int('m10rp11A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m10range0 = [ And(0 <= m10conxtI[i][j], 0 <= m10conxtA[i][j], 0 <= m10Q[i][j], 0 <= m10rp11I[i][j], 0 <= m10rp11A[i][j]) for i in range(n) for j in range(n) ]
s.add(m10range0)
m10range1 = [And(sLe(m10conxtI[i], principals), sLe(m10conxtA[i], principals), sLe(m10Q[i], principals), sLe(m10rp11I[i], principals), sLe(m10rp11A[i], principals)) for i in range(n)]
s.add(m10range1)
m10range2 = [And(0 <= m10H[i]) for i in range(n)]
s.add(m10range2)
s.add(sLe(m10H, principals))
s.add(Not(nonCheck(m10H)))
m11H = [ Int('m11H_%s' % i) for i in range(n) ] 
m11Q = [ [ Int('m11Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m11conxtC = [ Bool('m11conxtC_%s' % i) for i in range(n) ]
m11conxtI = [ [ Int('m11conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m11conxtA = [ [ Int('m11conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m11botC = [ Bool('m11botC_%s' % i) for i in range(n) ]
m11botI = [ [ Int('m11botI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m11botA = [ [ Int('m11botA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m11range0 = [ And(0 <= m11conxtI[i][j], 0 <= m11conxtA[i][j], 0 <= m11Q[i][j], 0 <= m11botI[i][j], 0 <= m11botA[i][j]) for i in range(n) for j in range(n) ]
s.add(m11range0)
m11range1 = [And(sLe(m11conxtI[i], principals), sLe(m11conxtA[i], principals), sLe(m11Q[i], principals), sLe(m11botI[i], principals), sLe(m11botA[i], principals)) for i in range(n)]
s.add(m11range1)
m11range2 = [And(0 <= m11H[i]) for i in range(n)]
s.add(m11range2)
s.add(sLe(m11H, principals))
s.add(Not(nonCheck(m11H)))
m12H = [ Int('m12H_%s' % i) for i in range(n) ] 
m12Q = [ [ Int('m12Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m12conxtC = [ Bool('m12conxtC_%s' % i) for i in range(n) ]
m12conxtI = [ [ Int('m12conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m12conxtA = [ [ Int('m12conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m12v13C = [ Bool('m12v13C_%s' % i) for i in range(n) ]
m12v13I = [ [ Int('m12v13I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m12v13A = [ [ Int('m12v13A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m12range0 = [ And(0 <= m12conxtI[i][j], 0 <= m12conxtA[i][j], 0 <= m12Q[i][j], 0 <= m12v13I[i][j], 0 <= m12v13A[i][j]) for i in range(n) for j in range(n) ]
s.add(m12range0)
m12range1 = [And(sLe(m12conxtI[i], principals), sLe(m12conxtA[i], principals), sLe(m12Q[i], principals), sLe(m12v13I[i], principals), sLe(m12v13A[i], principals)) for i in range(n)]
s.add(m12range1)
m12range2 = [And(0 <= m12H[i]) for i in range(n)]
s.add(m12range2)
s.add(sLe(m12H, principals))
s.add(Not(nonCheck(m12H)))
m13H = [ Int('m13H_%s' % i) for i in range(n) ] 
m13Q = [ [ Int('m13Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m13conxtC = [ Bool('m13conxtC_%s' % i) for i in range(n) ]
m13conxtI = [ [ Int('m13conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m13conxtA = [ [ Int('m13conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m13s1C = [ Bool('m13s1C_%s' % i) for i in range(n) ]
m13s1I = [ [ Int('m13s1I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m13s1A = [ [ Int('m13s1A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m13range0 = [ And(0 <= m13conxtI[i][j], 0 <= m13conxtA[i][j], 0 <= m13Q[i][j], 0 <= m13s1I[i][j], 0 <= m13s1A[i][j]) for i in range(n) for j in range(n) ]
s.add(m13range0)
m13range1 = [And(sLe(m13conxtI[i], principals), sLe(m13conxtA[i], principals), sLe(m13Q[i], principals), sLe(m13s1I[i], principals), sLe(m13s1A[i], principals)) for i in range(n)]
s.add(m13range1)
m13range2 = [And(0 <= m13H[i]) for i in range(n)]
s.add(m13range2)
s.add(sLe(m13H, principals))
s.add(Not(nonCheck(m13H)))
m14H = [ Int('m14H_%s' % i) for i in range(n) ] 
m14Q = [ [ Int('m14Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m14conxtC = [ Bool('m14conxtC_%s' % i) for i in range(n) ]
m14conxtI = [ [ Int('m14conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m14conxtA = [ [ Int('m14conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m14v12C = [ Bool('m14v12C_%s' % i) for i in range(n) ]
m14v12I = [ [ Int('m14v12I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m14v12A = [ [ Int('m14v12A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m14s1C = [ Bool('m14s1C_%s' % i) for i in range(n) ]
m14s1I = [ [ Int('m14s1I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m14s1A = [ [ Int('m14s1A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m14range0 = [ And(0 <= m14conxtI[i][j], 0 <= m14conxtA[i][j], 0 <= m14Q[i][j], 0 <= m14v12I[i][j], 0 <= m14v12A[i][j], 0 <= m14s1I[i][j], 0 <= m14s1A[i][j]) for i in range(n) for j in range(n) ]
s.add(m14range0)
m14range1 = [And(sLe(m14conxtI[i], principals), sLe(m14conxtA[i], principals), sLe(m14Q[i], principals), sLe(m14v12I[i], principals), sLe(m14v12A[i], principals), sLe(m14s1I[i], principals), sLe(m14s1A[i], principals)) for i in range(n)]
s.add(m14range1)
m14range2 = [And(0 <= m14H[i]) for i in range(n)]
s.add(m14range2)
s.add(sLe(m14H, principals))
s.add(Not(nonCheck(m14H)))
m15H = [ Int('m15H_%s' % i) for i in range(n) ] 
m15Q = [ [ Int('m15Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m15conxtC = [ Bool('m15conxtC_%s' % i) for i in range(n) ]
m15conxtI = [ [ Int('m15conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m15conxtA = [ [ Int('m15conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m15s1C = [ Bool('m15s1C_%s' % i) for i in range(n) ]
m15s1I = [ [ Int('m15s1I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m15s1A = [ [ Int('m15s1A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m15range0 = [ And(0 <= m15conxtI[i][j], 0 <= m15conxtA[i][j], 0 <= m15Q[i][j], 0 <= m15s1I[i][j], 0 <= m15s1A[i][j]) for i in range(n) for j in range(n) ]
s.add(m15range0)
m15range1 = [And(sLe(m15conxtI[i], principals), sLe(m15conxtA[i], principals), sLe(m15Q[i], principals), sLe(m15s1I[i], principals), sLe(m15s1A[i], principals)) for i in range(n)]
s.add(m15range1)
m15range2 = [And(0 <= m15H[i]) for i in range(n)]
s.add(m15range2)
s.add(sLe(m15H, principals))
s.add(Not(nonCheck(m15H)))
m16H = [ Int('m16H_%s' % i) for i in range(n) ] 
m16Q = [ [ Int('m16Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m16conxtC = [ Bool('m16conxtC_%s' % i) for i in range(n) ]
m16conxtI = [ [ Int('m16conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m16conxtA = [ [ Int('m16conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m16v11C = [ Bool('m16v11C_%s' % i) for i in range(n) ]
m16v11I = [ [ Int('m16v11I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m16v11A = [ [ Int('m16v11A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m16s1C = [ Bool('m16s1C_%s' % i) for i in range(n) ]
m16s1I = [ [ Int('m16s1I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m16s1A = [ [ Int('m16s1A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m16range0 = [ And(0 <= m16conxtI[i][j], 0 <= m16conxtA[i][j], 0 <= m16Q[i][j], 0 <= m16v11I[i][j], 0 <= m16v11A[i][j], 0 <= m16s1I[i][j], 0 <= m16s1A[i][j]) for i in range(n) for j in range(n) ]
s.add(m16range0)
m16range1 = [And(sLe(m16conxtI[i], principals), sLe(m16conxtA[i], principals), sLe(m16Q[i], principals), sLe(m16v11I[i], principals), sLe(m16v11A[i], principals), sLe(m16s1I[i], principals), sLe(m16s1A[i], principals)) for i in range(n)]
s.add(m16range1)
m16range2 = [And(0 <= m16H[i]) for i in range(n)]
s.add(m16range2)
s.add(sLe(m16H, principals))
s.add(Not(nonCheck(m16H)))
m17H = [ Int('m17H_%s' % i) for i in range(n) ] 
m17Q = [ [ Int('m17Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m17conxtC = [ Bool('m17conxtC_%s' % i) for i in range(n) ]
m17conxtI = [ [ Int('m17conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m17conxtA = [ [ Int('m17conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m17s1C = [ Bool('m17s1C_%s' % i) for i in range(n) ]
m17s1I = [ [ Int('m17s1I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m17s1A = [ [ Int('m17s1A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m17range0 = [ And(0 <= m17conxtI[i][j], 0 <= m17conxtA[i][j], 0 <= m17Q[i][j], 0 <= m17s1I[i][j], 0 <= m17s1A[i][j]) for i in range(n) for j in range(n) ]
s.add(m17range0)
m17range1 = [And(sLe(m17conxtI[i], principals), sLe(m17conxtA[i], principals), sLe(m17Q[i], principals), sLe(m17s1I[i], principals), sLe(m17s1A[i], principals)) for i in range(n)]
s.add(m17range1)
m17range2 = [And(0 <= m17H[i]) for i in range(n)]
s.add(m17range2)
s.add(sLe(m17H, principals))
s.add(Not(nonCheck(m17H)))
m18H = [ Int('m18H_%s' % i) for i in range(n) ] 
m18Q = [ [ Int('m18Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m18conxtC = [ Bool('m18conxtC_%s' % i) for i in range(n) ]
m18conxtI = [ [ Int('m18conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m18conxtA = [ [ Int('m18conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m18botC = [ Bool('m18botC_%s' % i) for i in range(n) ]
m18botI = [ [ Int('m18botI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m18botA = [ [ Int('m18botA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m18range0 = [ And(0 <= m18conxtI[i][j], 0 <= m18conxtA[i][j], 0 <= m18Q[i][j], 0 <= m18botI[i][j], 0 <= m18botA[i][j]) for i in range(n) for j in range(n) ]
s.add(m18range0)
m18range1 = [And(sLe(m18conxtI[i], principals), sLe(m18conxtA[i], principals), sLe(m18Q[i], principals), sLe(m18botI[i], principals), sLe(m18botA[i], principals)) for i in range(n)]
s.add(m18range1)
m18range2 = [And(0 <= m18H[i]) for i in range(n)]
s.add(m18range2)
s.add(sLe(m18H, principals))
s.add(Not(nonCheck(m18H)))
u1qs = [ [ Int("u1qs_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1qc = [ [ Int("u1qc_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1writep11input0C = [ Bool('u1writep11input0C_%s' % i) for i in range(n) ]
u1writep11input0I = [ [ Int("u1writep11input0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1writep11input0A = [ [ Int("u1writep11input0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1writep11outputC = [ Bool('u1writep11outputC_%s' % i) for i in range(n) ]
u1writep11outputI = [ [ Int("u1writep11outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1writep11outputA = [ [ Int("u1writep11outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1writep13input0C = [ Bool('u1writep13input0C_%s' % i) for i in range(n) ]
u1writep13input0I = [ [ Int("u1writep13input0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1writep13input0A = [ [ Int("u1writep13input0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1writep13outputC = [ Bool('u1writep13outputC_%s' % i) for i in range(n) ]
u1writep13outputI = [ [ Int("u1writep13outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1writep13outputA = [ [ Int("u1writep13outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1writep12input0C = [ Bool('u1writep12input0C_%s' % i) for i in range(n) ]
u1writep12input0I = [ [ Int("u1writep12input0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1writep12input0A = [ [ Int("u1writep12input0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1writep12outputC = [ Bool('u1writep12outputC_%s' % i) for i in range(n) ]
u1writep12outputI = [ [ Int("u1writep12outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1writep12outputA = [ [ Int("u1writep12outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1random2input0C = [ True, False, False ]
u1random2input0I = [ [ Int("u1random2input0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1random2input0A = [ [ Int("u1random2input0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1random2outputC = [ True, True, False ]
u1random2outputI = [ [ Int("u1random2outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1random2outputA = [ [ Int("u1random2outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1random1input0C = [ Bool('u1random1input0C_%s' % i) for i in range(n) ]
u1random1input0I = [ [ Int("u1random1input0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1random1input0A = [ [ Int("u1random1input0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1random1outputC = [ Bool('u1random1outputC_%s' % i) for i in range(n) ]
u1random1outputI = [ [ Int("u1random1outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1random1outputA = [ [ Int("u1random1outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1random3input0C = [ True, False, False ]
u1random3input0I = [ [ Int("u1random3input0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1random3input0A = [ [ Int("u1random3input0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1random3outputC = [ True, False, True ]
u1random3outputI = [ [ Int("u1random3outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1random3outputA = [ [ Int("u1random3outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1readp21botC = [ Bool('u1readp21botC_%s' % i) for i in range(n) ]
u1readp21botI = [ [ Int("u1readp21botI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1readp21botA = [ [ Int("u1readp21botA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1readp21outputC = [ True, True, False ]
u1readp21outputI = [ [ Int("u1readp21outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1readp21outputA = [ [ Int("u1readp21outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1getSalarybotC = [ Bool('u1getSalarybotC_%s' % i) for i in range(n) ]
u1getSalarybotI = [ [ Int("u1getSalarybotI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1getSalarybotA = [ [ Int("u1getSalarybotA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1getSalaryoutputC = [ True, False, False ]
u1getSalaryoutputI = [ [ Int("u1getSalaryoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1getSalaryoutputA = [ [ Int("u1getSalaryoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1readp31botC = [ Bool('u1readp31botC_%s' % i) for i in range(n) ]
u1readp31botI = [ [ Int("u1readp31botI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1readp31botA = [ [ Int("u1readp31botA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1readp31outputC = [ True, False, True ]
u1readp31outputI = [ [ Int("u1readp31outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1readp31outputA = [ [ Int("u1readp31outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1readp11botC = [ Bool('u1readp11botC_%s' % i) for i in range(n) ]
u1readp11botI = [ [ Int("u1readp11botI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1readp11botA = [ [ Int("u1readp11botA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1readp11outputC = [ True, False, False ]
u1readp11outputI = [ [ Int("u1readp11outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1readp11outputA = [ [ Int("u1readp11outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1computeSum1input0C = [ True, False, False ]
u1computeSum1input0I = [ [ Int("u1computeSum1input0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1computeSum1input0A = [ [ Int("u1computeSum1input0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1computeSum1input1C = [ True, True, False ]
u1computeSum1input1I = [ [ Int("u1computeSum1input1I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1computeSum1input1A = [ [ Int("u1computeSum1input1A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1computeSum1input2C = [ True, False, True ]
u1computeSum1input2I = [ [ Int("u1computeSum1input2I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1computeSum1input2A = [ [ Int("u1computeSum1input2A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1computeSum1outputC = [ True, True, True ]
u1computeSum1outputI = [ [ Int("u1computeSum1outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1computeSum1outputA = [ [ Int("u1computeSum1outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u1range0 = [ And(0 <= u1qs[i][j], 0 <= u1qc[i][j], 0 <= u1writep11outputI[i][j], 0 <= u1writep11outputA[i][j], 0 <= u1writep11input0I[i][j], 0 <= u1writep11input0A[i][j], 0 <= u1writep13outputI[i][j], 0 <= u1writep13outputA[i][j], 0 <= u1writep13input0I[i][j], 0 <= u1writep13input0A[i][j], 0 <= u1writep12outputI[i][j], 0 <= u1writep12outputA[i][j], 0 <= u1writep12input0I[i][j], 0 <= u1writep12input0A[i][j], 0 <= u1random2outputI[i][j], 0 <= u1random2outputA[i][j], 0 <= u1random2input0I[i][j], 0 <= u1random2input0A[i][j], 0 <= u1random1outputI[i][j], 0 <= u1random1outputA[i][j], 0 <= u1random1input0I[i][j], 0 <= u1random1input0A[i][j], 0 <= u1random3outputI[i][j], 0 <= u1random3outputA[i][j], 0 <= u1random3input0I[i][j], 0 <= u1random3input0A[i][j], 0 <= u1readp21outputI[i][j], 0 <= u1readp21outputA[i][j], 0 <= u1readp21botI[i][j], 0 <= u1readp21botA[i][j], 0 <= u1getSalaryoutputI[i][j], 0 <= u1getSalaryoutputA[i][j], 0 <= u1getSalarybotI[i][j], 0 <= u1getSalarybotA[i][j], 0 <= u1readp31outputI[i][j], 0 <= u1readp31outputA[i][j], 0 <= u1readp31botI[i][j], 0 <= u1readp31botA[i][j], 0 <= u1readp11outputI[i][j], 0 <= u1readp11outputA[i][j], 0 <= u1readp11botI[i][j], 0 <= u1readp11botA[i][j], 0 <= u1computeSum1outputI[i][j], 0 <= u1computeSum1outputA[i][j], 0 <= u1computeSum1input0I[i][j], 0 <= u1computeSum1input0A[i][j], 0 <= u1computeSum1input1I[i][j], 0 <= u1computeSum1input1A[i][j], 0 <= u1computeSum1input2I[i][j], 0 <= u1computeSum1input2A[i][j]) for i in range(n) for j in range(n) ]
s.add(u1range0)
u1range1 = [And(sLe(u1qs[i], principals), sLe(u1qc[i], principals), sLe(u1writep11outputI[i], principals), sLe(u1writep11outputA[i], principals), sLe(u1writep11input0I[i], principals), sLe(u1writep11input0A[i], principals), sLe(u1writep13outputI[i], principals), sLe(u1writep13outputA[i], principals), sLe(u1writep13input0I[i], principals), sLe(u1writep13input0A[i], principals), sLe(u1writep12outputI[i], principals), sLe(u1writep12outputA[i], principals), sLe(u1writep12input0I[i], principals), sLe(u1writep12input0A[i], principals), sLe(u1random2outputI[i], principals), sLe(u1random2outputA[i], principals), sLe(u1random2input0I[i], principals), sLe(u1random2input0A[i], principals), sLe(u1random1outputI[i], principals), sLe(u1random1outputA[i], principals), sLe(u1random1input0I[i], principals), sLe(u1random1input0A[i], principals), sLe(u1random3outputI[i], principals), sLe(u1random3outputA[i], principals), sLe(u1random3input0I[i], principals), sLe(u1random3input0A[i], principals), sLe(u1readp21outputI[i], principals), sLe(u1readp21outputA[i], principals), sLe(u1readp21botI[i], principals), sLe(u1readp21botA[i], principals), sLe(u1getSalaryoutputI[i], principals), sLe(u1getSalaryoutputA[i], principals), sLe(u1getSalarybotI[i], principals), sLe(u1getSalarybotA[i], principals), sLe(u1readp31outputI[i], principals), sLe(u1readp31outputA[i], principals), sLe(u1readp31botI[i], principals), sLe(u1readp31botA[i], principals), sLe(u1readp11outputI[i], principals), sLe(u1readp11outputA[i], principals), sLe(u1readp11botI[i], principals), sLe(u1readp11botA[i], principals), sLe(u1computeSum1outputI[i], principals), sLe(u1computeSum1outputA[i], principals), sLe(u1computeSum1input0I[i], principals), sLe(u1computeSum1input0A[i], principals), sLe(u1computeSum1input1I[i], principals), sLe(u1computeSum1input1A[i], principals), sLe(u1computeSum1input2I[i], principals), sLe(u1computeSum1input2A[i], principals)) for i in range(n)]
s.add(u1range1)
u2qs = [ [ Int("u2qs_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2qc = [ [ Int("u2qc_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2readp32botC = [ Bool('u2readp32botC_%s' % i) for i in range(n) ]
u2readp32botI = [ [ Int("u2readp32botI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2readp32botA = [ [ Int("u2readp32botA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2readp32outputC = [ False, True, True ]
u2readp32outputI = [ [ Int("u2readp32outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2readp32outputA = [ [ Int("u2readp32outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2computeSum2input0C = [ True, True, False ]
u2computeSum2input0I = [ [ Int("u2computeSum2input0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2computeSum2input0A = [ [ Int("u2computeSum2input0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2computeSum2input1C = [ False, True, False ]
u2computeSum2input1I = [ [ Int("u2computeSum2input1I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2computeSum2input1A = [ [ Int("u2computeSum2input1A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2computeSum2input2C = [ False, True, True ]
u2computeSum2input2I = [ [ Int("u2computeSum2input2I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2computeSum2input2A = [ [ Int("u2computeSum2input2A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2computeSum2outputC = [ True, True, True ]
u2computeSum2outputI = [ [ Int("u2computeSum2outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2computeSum2outputA = [ [ Int("u2computeSum2outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2readp12botC = [ Bool('u2readp12botC_%s' % i) for i in range(n) ]
u2readp12botI = [ [ Int("u2readp12botI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2readp12botA = [ [ Int("u2readp12botA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2readp12outputC = [ True, True, False ]
u2readp12outputI = [ [ Int("u2readp12outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2readp12outputA = [ [ Int("u2readp12outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2readp22botC = [ Bool('u2readp22botC_%s' % i) for i in range(n) ]
u2readp22botI = [ [ Int("u2readp22botI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2readp22botA = [ [ Int("u2readp22botA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2readp22outputC = [ False, True, False ]
u2readp22outputI = [ [ Int("u2readp22outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2readp22outputA = [ [ Int("u2readp22outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u2range0 = [ And(0 <= u2qs[i][j], 0 <= u2qc[i][j], 0 <= u2readp32outputI[i][j], 0 <= u2readp32outputA[i][j], 0 <= u2readp32botI[i][j], 0 <= u2readp32botA[i][j], 0 <= u2computeSum2outputI[i][j], 0 <= u2computeSum2outputA[i][j], 0 <= u2computeSum2input0I[i][j], 0 <= u2computeSum2input0A[i][j], 0 <= u2computeSum2input1I[i][j], 0 <= u2computeSum2input1A[i][j], 0 <= u2computeSum2input2I[i][j], 0 <= u2computeSum2input2A[i][j], 0 <= u2readp12outputI[i][j], 0 <= u2readp12outputA[i][j], 0 <= u2readp12botI[i][j], 0 <= u2readp12botA[i][j], 0 <= u2readp22outputI[i][j], 0 <= u2readp22outputA[i][j], 0 <= u2readp22botI[i][j], 0 <= u2readp22botA[i][j]) for i in range(n) for j in range(n) ]
s.add(u2range0)
u2range1 = [And(sLe(u2qs[i], principals), sLe(u2qc[i], principals), sLe(u2readp32outputI[i], principals), sLe(u2readp32outputA[i], principals), sLe(u2readp32botI[i], principals), sLe(u2readp32botA[i], principals), sLe(u2computeSum2outputI[i], principals), sLe(u2computeSum2outputA[i], principals), sLe(u2computeSum2input0I[i], principals), sLe(u2computeSum2input0A[i], principals), sLe(u2computeSum2input1I[i], principals), sLe(u2computeSum2input1A[i], principals), sLe(u2computeSum2input2I[i], principals), sLe(u2computeSum2input2A[i], principals), sLe(u2readp12outputI[i], principals), sLe(u2readp12outputA[i], principals), sLe(u2readp12botI[i], principals), sLe(u2readp12botA[i], principals), sLe(u2readp22outputI[i], principals), sLe(u2readp22outputA[i], principals), sLe(u2readp22botI[i], principals), sLe(u2readp22botA[i], principals)) for i in range(n)]
s.add(u2range1)
u3qs = [ [ Int("u3qs_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3qc = [ [ Int("u3qc_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3readp13botC = [ Bool('u3readp13botC_%s' % i) for i in range(n) ]
u3readp13botI = [ [ Int("u3readp13botI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3readp13botA = [ [ Int("u3readp13botA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3readp13outputC = [ True, False, True ]
u3readp13outputI = [ [ Int("u3readp13outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3readp13outputA = [ [ Int("u3readp13outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3computeSum3input0C = [ True, False, True ]
u3computeSum3input0I = [ [ Int("u3computeSum3input0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3computeSum3input0A = [ [ Int("u3computeSum3input0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3computeSum3input1C = [ False, True, True ]
u3computeSum3input1I = [ [ Int("u3computeSum3input1I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3computeSum3input1A = [ [ Int("u3computeSum3input1A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3computeSum3input2C = [ False, False, True ]
u3computeSum3input2I = [ [ Int("u3computeSum3input2I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3computeSum3input2A = [ [ Int("u3computeSum3input2A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3computeSum3outputC = [ True, True, True ]
u3computeSum3outputI = [ [ Int("u3computeSum3outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3computeSum3outputA = [ [ Int("u3computeSum3outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3readp23botC = [ Bool('u3readp23botC_%s' % i) for i in range(n) ]
u3readp23botI = [ [ Int("u3readp23botI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3readp23botA = [ [ Int("u3readp23botA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3readp23outputC = [ False, True, True ]
u3readp23outputI = [ [ Int("u3readp23outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3readp23outputA = [ [ Int("u3readp23outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3readp33botC = [ Bool('u3readp33botC_%s' % i) for i in range(n) ]
u3readp33botI = [ [ Int("u3readp33botI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3readp33botA = [ [ Int("u3readp33botA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3readp33outputC = [ False, False, True ]
u3readp33outputI = [ [ Int("u3readp33outputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3readp33outputA = [ [ Int("u3readp33outputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
u3range0 = [ And(0 <= u3qs[i][j], 0 <= u3qc[i][j], 0 <= u3readp13outputI[i][j], 0 <= u3readp13outputA[i][j], 0 <= u3readp13botI[i][j], 0 <= u3readp13botA[i][j], 0 <= u3computeSum3outputI[i][j], 0 <= u3computeSum3outputA[i][j], 0 <= u3computeSum3input0I[i][j], 0 <= u3computeSum3input0A[i][j], 0 <= u3computeSum3input1I[i][j], 0 <= u3computeSum3input1A[i][j], 0 <= u3computeSum3input2I[i][j], 0 <= u3computeSum3input2A[i][j], 0 <= u3readp23outputI[i][j], 0 <= u3readp23outputA[i][j], 0 <= u3readp23botI[i][j], 0 <= u3readp23botA[i][j], 0 <= u3readp33outputI[i][j], 0 <= u3readp33outputA[i][j], 0 <= u3readp33botI[i][j], 0 <= u3readp33botA[i][j]) for i in range(n) for j in range(n) ]
s.add(u3range0)
u3range1 = [And(sLe(u3qs[i], principals), sLe(u3qc[i], principals), sLe(u3readp13outputI[i], principals), sLe(u3readp13outputA[i], principals), sLe(u3readp13botI[i], principals), sLe(u3readp13botA[i], principals), sLe(u3computeSum3outputI[i], principals), sLe(u3computeSum3outputA[i], principals), sLe(u3computeSum3input0I[i], principals), sLe(u3computeSum3input0A[i], principals), sLe(u3computeSum3input1I[i], principals), sLe(u3computeSum3input1A[i], principals), sLe(u3computeSum3input2I[i], principals), sLe(u3computeSum3input2A[i], principals), sLe(u3readp23outputI[i], principals), sLe(u3readp23outputA[i], principals), sLe(u3readp23botI[i], principals), sLe(u3readp23botA[i], principals), sLe(u3readp33outputI[i], principals), sLe(u3readp33outputA[i], principals), sLe(u3readp33botI[i], principals), sLe(u3readp33botA[i], principals)) for i in range(n)]
s.add(u3range1)
#FieldT: u1
s.add(confQ(u1writep11outputC, u1qs))
s.add(sIntegrity(u1writep11outputI, u1qs))
s.add(availabilityC(u1writep11outputA, u1qs))
s.add(cIntegrityE(u1writep11input0I, u1qc))
s.add(lableLe(u1writep11input0C, u1writep11outputC, u1writep11input0I, u1writep11outputI, u1writep11input0A, u1writep11outputA))
s.add(confQ(u1writep13outputC, u1qs))
s.add(sIntegrity(u1writep13outputI, u1qs))
s.add(availabilityC(u1writep13outputA, u1qs))
s.add(cIntegrityE(u1writep13input0I, u1qc))
s.add(lableLe(u1writep13input0C, u1writep13outputC, u1writep13input0I, u1writep13outputI, u1writep13input0A, u1writep13outputA))
s.add(confQ(u1writep12outputC, u1qs))
s.add(sIntegrity(u1writep12outputI, u1qs))
s.add(availabilityC(u1writep12outputA, u1qs))
s.add(cIntegrityE(u1writep12input0I, u1qc))
s.add(lableLe(u1writep12input0C, u1writep12outputC, u1writep12input0I, u1writep12outputI, u1writep12input0A, u1writep12outputA))
s.add(confQ(u1random2outputC, u1qs))
s.add(sIntegrity(u1random2outputI, u1qs))
s.add(availabilityC(u1random2outputA, u1qs))
s.add(cIntegrityE(u1random2input0I, u1qc))
s.add(bLe(u1random2outputI, u1random2input0I))
s.add(bLe(u1random2outputA, u1random2input0A))
s.add(confQ(u1random1outputC, u1qs))
s.add(sIntegrity(u1random1outputI, u1qs))
s.add(availabilityC(u1random1outputA, u1qs))
s.add(cIntegrityE(u1random1input0I, u1qc))
s.add(lableLe(u1random1input0C, u1random1outputC, u1random1input0I, u1random1outputI, u1random1input0A, u1random1outputA))
s.add(confQ(u1random3outputC, u1qs))
s.add(sIntegrity(u1random3outputI, u1qs))
s.add(availabilityC(u1random3outputA, u1qs))
s.add(cIntegrityE(u1random3input0I, u1qc))
s.add(bLe(u1random3outputI, u1random3input0I))
s.add(bLe(u1random3outputA, u1random3input0A))
s.add(confQ(u1readp21outputC, u1qs))
s.add(sIntegrity(u1readp21outputI, u1qs))
s.add(availabilityC(u1readp21outputA, u1qs))
s.add(cIntegrityE(u1readp21botI, u1qc))
s.add(lableLe(u1readp21botC, u1readp21outputC, u1readp21botI, u1readp21outputI, u1readp21botA, u1readp21outputA))
s.add(confQ(u1getSalaryoutputC, u1qs))
s.add(sIntegrity(u1getSalaryoutputI, u1qs))
s.add(availabilityC(u1getSalaryoutputA, u1qs))
s.add(cIntegrityE(u1getSalarybotI, u1qc))
s.add(lableLe(u1getSalarybotC, u1getSalaryoutputC, u1getSalarybotI, u1getSalaryoutputI, u1getSalarybotA, u1getSalaryoutputA))
s.add(confQ(u1readp31outputC, u1qs))
s.add(sIntegrity(u1readp31outputI, u1qs))
s.add(availabilityC(u1readp31outputA, u1qs))
s.add(cIntegrityE(u1readp31botI, u1qc))
s.add(lableLe(u1readp31botC, u1readp31outputC, u1readp31botI, u1readp31outputI, u1readp31botA, u1readp31outputA))
s.add(confQ(u1readp11outputC, u1qs))
s.add(sIntegrity(u1readp11outputI, u1qs))
s.add(availabilityC(u1readp11outputA, u1qs))
s.add(cIntegrityE(u1readp11botI, u1qc))
s.add(lableLe(u1readp11botC, u1readp11outputC, u1readp11botI, u1readp11outputI, u1readp11botA, u1readp11outputA))
s.add(confQ(u1computeSum1outputC, u1qs))
s.add(sIntegrity(u1computeSum1outputI, u1qs))
s.add(availabilityC(u1computeSum1outputA, u1qs))
s.add(cIntegrityE(u1computeSum1input0I, u1qc))
s.add(bLe(u1computeSum1outputI, u1computeSum1input0I))
s.add(bLe(u1computeSum1outputA, u1computeSum1input0A))
s.add(cIntegrityE(u1computeSum1input1I, u1qc))
s.add(bLe(u1computeSum1outputI, u1computeSum1input1I))
s.add(bLe(u1computeSum1outputA, u1computeSum1input1A))
s.add(cIntegrityE(u1computeSum1input2I, u1qc))
s.add(bLe(u1computeSum1outputI, u1computeSum1input2I))
s.add(bLe(u1computeSum1outputA, u1computeSum1input2A))
#FieldT: u2
s.add(confQ(u2readp32outputC, u2qs))
s.add(sIntegrity(u2readp32outputI, u2qs))
s.add(availabilityC(u2readp32outputA, u2qs))
s.add(cIntegrityE(u2readp32botI, u2qc))
s.add(lableLe(u2readp32botC, u2readp32outputC, u2readp32botI, u2readp32outputI, u2readp32botA, u2readp32outputA))
s.add(confQ(u2computeSum2outputC, u2qs))
s.add(sIntegrity(u2computeSum2outputI, u2qs))
s.add(availabilityC(u2computeSum2outputA, u2qs))
s.add(cIntegrityE(u2computeSum2input0I, u2qc))
s.add(bLe(u2computeSum2outputI, u2computeSum2input0I))
s.add(bLe(u2computeSum2outputA, u2computeSum2input0A))
s.add(cIntegrityE(u2computeSum2input1I, u2qc))
s.add(bLe(u2computeSum2outputI, u2computeSum2input1I))
s.add(bLe(u2computeSum2outputA, u2computeSum2input1A))
s.add(cIntegrityE(u2computeSum2input2I, u2qc))
s.add(bLe(u2computeSum2outputI, u2computeSum2input2I))
s.add(bLe(u2computeSum2outputA, u2computeSum2input2A))
s.add(confQ(u2readp12outputC, u2qs))
s.add(sIntegrity(u2readp12outputI, u2qs))
s.add(availabilityC(u2readp12outputA, u2qs))
s.add(cIntegrityE(u2readp12botI, u2qc))
s.add(lableLe(u2readp12botC, u2readp12outputC, u2readp12botI, u2readp12outputI, u2readp12botA, u2readp12outputA))
s.add(confQ(u2readp22outputC, u2qs))
s.add(sIntegrity(u2readp22outputI, u2qs))
s.add(availabilityC(u2readp22outputA, u2qs))
s.add(cIntegrityE(u2readp22botI, u2qc))
s.add(lableLe(u2readp22botC, u2readp22outputC, u2readp22botI, u2readp22outputI, u2readp22botA, u2readp22outputA))
#FieldT: u3
s.add(confQ(u3readp13outputC, u3qs))
s.add(sIntegrity(u3readp13outputI, u3qs))
s.add(availabilityC(u3readp13outputA, u3qs))
s.add(cIntegrityE(u3readp13botI, u3qc))
s.add(lableLe(u3readp13botC, u3readp13outputC, u3readp13botI, u3readp13outputI, u3readp13botA, u3readp13outputA))
s.add(confQ(u3computeSum3outputC, u3qs))
s.add(sIntegrity(u3computeSum3outputI, u3qs))
s.add(availabilityC(u3computeSum3outputA, u3qs))
s.add(cIntegrityE(u3computeSum3input0I, u3qc))
s.add(bLe(u3computeSum3outputI, u3computeSum3input0I))
s.add(bLe(u3computeSum3outputA, u3computeSum3input0A))
s.add(cIntegrityE(u3computeSum3input1I, u3qc))
s.add(bLe(u3computeSum3outputI, u3computeSum3input1I))
s.add(bLe(u3computeSum3outputA, u3computeSum3input1A))
s.add(cIntegrityE(u3computeSum3input2I, u3qc))
s.add(bLe(u3computeSum3outputI, u3computeSum3input2I))
s.add(bLe(u3computeSum3outputA, u3computeSum3input2A))
s.add(confQ(u3readp23outputC, u3qs))
s.add(sIntegrity(u3readp23outputI, u3qs))
s.add(availabilityC(u3readp23outputA, u3qs))
s.add(cIntegrityE(u3readp23botI, u3qc))
s.add(lableLe(u3readp23botC, u3readp23outputC, u3readp23botI, u3readp23outputI, u3readp23botA, u3readp23outputA))
s.add(confQ(u3readp33outputC, u3qs))
s.add(sIntegrity(u3readp33outputI, u3qs))
s.add(availabilityC(u3readp33outputA, u3qs))
s.add(cIntegrityE(u3readp33botI, u3qc))
s.add(lableLe(u3readp33botC, u3readp33outputC, u3readp33botI, u3readp33outputI, u3readp33botA, u3readp33outputA))
#MethodT: m18
m18botC = m18conxtC
m18botI = m18conxtI
m18botA = m18conxtA
#ObjCallT: let s1 = u1.getSalary() in let v11 = u1.random1(s1) in let x25 = u1.writep11(v11) in let v12 = u1.random2(s1) in let x22 = u1.writep12(v12) in let v13 = u1.random3(s1) in let x19 = u1.writep13(v13) in let rp11 = u1.readp11() in let rp21 = u1.readp21() in let rp31 = u1.readp31() in let sum1 = u1.computeSum1(rp11, rp21, rp31) in let rp12 = u2.readp12() in let rp22 = u2.readp22() in let rp32 = u2.readp32() in let sum2 = u2.computeSum2(rp12, rp22, rp32) in let rp13 = u3.readp13() in let rp23 = u3.readp23() in let rp33 = u3.readp33() in let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u1getSalaryoutputC, m18H))
s.add(availabilityP(u1getSalarybotA, u1qc, m18H))
#ThisCallT: this.m17(s1)
s.add(cLe(m18conxtC, m17conxtC))
s.add(bLe(m17conxtI, m18conxtI))
s.add(bLe(m17conxtA, m18conxtA))
s.add(cLe(u1getSalaryoutputC, m17s1C))
s.add(cLe(botC, m17s1C))
s.add(bLe(m17s1I, u1getSalaryoutputI))
s.add(bLe(m17s1I, botI))
s.add(bLe(m17s1A, u1getSalaryoutputA))
s.add(bLe(m17s1A, botA))
s.add(cLe(m18conxtC, m17s1C))
s.add(bLe(m17s1I, m18conxtI))
s.add(bLe(m17s1A, m18conxtA))
s.add(availabilityP(m17s1A, m17Q, m18H))
s.add(cIntegrityE(m18botI, m18Q))
s.add(cLeH(m18botC, m18H))
s.add(cLeH(m18conxtC, m18H))
#MethodT: m17
#ObjCallT: let v11 = u1.random1(s1) in let x25 = u1.writep11(v11) in let v12 = u1.random2(s1) in let x22 = u1.writep12(v12) in let v13 = u1.random3(s1) in let x19 = u1.writep13(v13) in let rp11 = u1.readp11() in let rp21 = u1.readp21() in let rp31 = u1.readp31() in let sum1 = u1.computeSum1(rp11, rp21, rp31) in let rp12 = u2.readp12() in let rp22 = u2.readp22() in let rp32 = u2.readp32() in let sum2 = u2.computeSum2(rp12, rp22, rp32) in let rp13 = u3.readp13() in let rp23 = u3.readp23() in let rp33 = u3.readp33() in let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u1random1outputC, m17H))
s.add(cLe(m17s1C, u1random1input0C))
s.add(cLe(botC, u1random1input0C))
s.add(bLe(u1random1input0I, m17s1I))
s.add(bLe(u1random1input0I, botI))
s.add(bLe(u1random1input0A, m17s1A))
s.add(bLe(u1random1input0A, botA))
s.add(availabilityP(u1random1input0A, u1qc, m17H))
#ThisCallT: this.m16(v11, s1)
s.add(cLe(m17conxtC, m16conxtC))
s.add(bLe(m16conxtI, m17conxtI))
s.add(bLe(m16conxtA, m17conxtA))
s.add(cLe(u1random1outputC, m16v11C))
s.add(cLe(botC, m16v11C))
s.add(bLe(m16v11I, u1random1outputI))
s.add(bLe(m16v11I, botI))
s.add(bLe(m16v11A, u1random1outputA))
s.add(bLe(m16v11A, botA))
s.add(cLe(m17conxtC, m16v11C))
s.add(bLe(m16v11I, m17conxtI))
s.add(bLe(m16v11A, m17conxtA))
s.add(availabilityP(m16v11A, m16Q, m17H))
s.add(cLe(m17s1C, m16s1C))
s.add(cLe(botC, m16s1C))
s.add(cLe(botC, m16s1C))
s.add(bLe(m16s1I, m17s1I))
s.add(bLe(m16s1I, botI))
s.add(bLe(m16s1I, botI))
s.add(bLe(m16s1A, m17s1A))
s.add(bLe(m16s1A, botA))
s.add(bLe(m16s1A, botA))
s.add(cLe(m17conxtC, m16s1C))
s.add(bLe(m16s1I, m17conxtI))
s.add(bLe(m16s1A, m17conxtA))
s.add(availabilityP(m16s1A, m16Q, m17H))
s.add(cIntegrityE(m17s1I, m17Q))
s.add(cLeH(m17s1C, m17H))
s.add(cLeH(m17conxtC, m17H))
#MethodT: m16
#ObjCallT: let x25 = u1.writep11(v11) in let v12 = u1.random2(s1) in let x22 = u1.writep12(v12) in let v13 = u1.random3(s1) in let x19 = u1.writep13(v13) in let rp11 = u1.readp11() in let rp21 = u1.readp21() in let rp31 = u1.readp31() in let sum1 = u1.computeSum1(rp11, rp21, rp31) in let rp12 = u2.readp12() in let rp22 = u2.readp22() in let rp32 = u2.readp32() in let sum2 = u2.computeSum2(rp12, rp22, rp32) in let rp13 = u3.readp13() in let rp23 = u3.readp23() in let rp33 = u3.readp33() in let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u1writep11outputC, m16H))
s.add(cLe(m16v11C, u1writep11input0C))
s.add(cLe(botC, u1writep11input0C))
s.add(bLe(u1writep11input0I, m16v11I))
s.add(bLe(u1writep11input0I, botI))
s.add(bLe(u1writep11input0A, m16v11A))
s.add(bLe(u1writep11input0A, botA))
s.add(availabilityP(u1writep11input0A, u1qc, m16H))
#ThisCallT: this.m15(s1)
s.add(cLe(m16conxtC, m15conxtC))
s.add(bLe(m15conxtI, m16conxtI))
s.add(bLe(m15conxtA, m16conxtA))
s.add(cLe(m16s1C, m15s1C))
s.add(cLe(botC, m15s1C))
s.add(bLe(m15s1I, m16s1I))
s.add(bLe(m15s1I, botI))
s.add(bLe(m15s1A, m16s1A))
s.add(bLe(m15s1A, botA))
s.add(cLe(m16conxtC, m15s1C))
s.add(bLe(m15s1I, m16conxtI))
s.add(bLe(m15s1A, m16conxtA))
s.add(availabilityP(m15s1A, m15Q, m16H))
s.add(cIntegrityE(m16v11I, m16Q))
s.add(cIntegrityE(m16s1I, m16Q))
s.add(cLeH(m16v11C, m16H))
s.add(cLeH(m16s1C, m16H))
s.add(cLeH(m16conxtC, m16H))
#MethodT: m15
#ObjCallT: let v12 = u1.random2(s1) in let x22 = u1.writep12(v12) in let v13 = u1.random3(s1) in let x19 = u1.writep13(v13) in let rp11 = u1.readp11() in let rp21 = u1.readp21() in let rp31 = u1.readp31() in let sum1 = u1.computeSum1(rp11, rp21, rp31) in let rp12 = u2.readp12() in let rp22 = u2.readp22() in let rp32 = u2.readp32() in let sum2 = u2.computeSum2(rp12, rp22, rp32) in let rp13 = u3.readp13() in let rp23 = u3.readp23() in let rp33 = u3.readp33() in let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u1random2outputC, m15H))
s.add(cLe(m15s1C, u1random2input0C))
s.add(cLe(botC, u1random2input0C))
s.add(bLe(u1random2input0I, m15s1I))
s.add(bLe(u1random2input0I, botI))
s.add(bLe(u1random2input0A, m15s1A))
s.add(bLe(u1random2input0A, botA))
s.add(availabilityP(u1random2input0A, u1qc, m15H))
#ThisCallT: this.m14(v12, s1)
s.add(cLe(m15conxtC, m14conxtC))
s.add(bLe(m14conxtI, m15conxtI))
s.add(bLe(m14conxtA, m15conxtA))
s.add(cLe(u1random2outputC, m14v12C))
s.add(cLe(botC, m14v12C))
s.add(bLe(m14v12I, u1random2outputI))
s.add(bLe(m14v12I, botI))
s.add(bLe(m14v12A, u1random2outputA))
s.add(bLe(m14v12A, botA))
s.add(cLe(m15conxtC, m14v12C))
s.add(bLe(m14v12I, m15conxtI))
s.add(bLe(m14v12A, m15conxtA))
s.add(availabilityP(m14v12A, m14Q, m15H))
s.add(cLe(m15s1C, m14s1C))
s.add(cLe(botC, m14s1C))
s.add(cLe(botC, m14s1C))
s.add(bLe(m14s1I, m15s1I))
s.add(bLe(m14s1I, botI))
s.add(bLe(m14s1I, botI))
s.add(bLe(m14s1A, m15s1A))
s.add(bLe(m14s1A, botA))
s.add(bLe(m14s1A, botA))
s.add(cLe(m15conxtC, m14s1C))
s.add(bLe(m14s1I, m15conxtI))
s.add(bLe(m14s1A, m15conxtA))
s.add(availabilityP(m14s1A, m14Q, m15H))
s.add(cIntegrityE(m15s1I, m15Q))
s.add(cLeH(m15s1C, m15H))
s.add(cLeH(m15conxtC, m15H))
#MethodT: m14
#ObjCallT: let x22 = u1.writep12(v12) in let v13 = u1.random3(s1) in let x19 = u1.writep13(v13) in let rp11 = u1.readp11() in let rp21 = u1.readp21() in let rp31 = u1.readp31() in let sum1 = u1.computeSum1(rp11, rp21, rp31) in let rp12 = u2.readp12() in let rp22 = u2.readp22() in let rp32 = u2.readp32() in let sum2 = u2.computeSum2(rp12, rp22, rp32) in let rp13 = u3.readp13() in let rp23 = u3.readp23() in let rp33 = u3.readp33() in let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u1writep12outputC, m14H))
s.add(cLe(m14v12C, u1writep12input0C))
s.add(cLe(botC, u1writep12input0C))
s.add(bLe(u1writep12input0I, m14v12I))
s.add(bLe(u1writep12input0I, botI))
s.add(bLe(u1writep12input0A, m14v12A))
s.add(bLe(u1writep12input0A, botA))
s.add(availabilityP(u1writep12input0A, u1qc, m14H))
#ThisCallT: this.m13(s1)
s.add(cLe(m14conxtC, m13conxtC))
s.add(bLe(m13conxtI, m14conxtI))
s.add(bLe(m13conxtA, m14conxtA))
s.add(cLe(m14s1C, m13s1C))
s.add(cLe(botC, m13s1C))
s.add(bLe(m13s1I, m14s1I))
s.add(bLe(m13s1I, botI))
s.add(bLe(m13s1A, m14s1A))
s.add(bLe(m13s1A, botA))
s.add(cLe(m14conxtC, m13s1C))
s.add(bLe(m13s1I, m14conxtI))
s.add(bLe(m13s1A, m14conxtA))
s.add(availabilityP(m13s1A, m13Q, m14H))
s.add(cIntegrityE(m14v12I, m14Q))
s.add(cIntegrityE(m14s1I, m14Q))
s.add(cLeH(m14v12C, m14H))
s.add(cLeH(m14s1C, m14H))
s.add(cLeH(m14conxtC, m14H))
#MethodT: m13
#ObjCallT: let v13 = u1.random3(s1) in let x19 = u1.writep13(v13) in let rp11 = u1.readp11() in let rp21 = u1.readp21() in let rp31 = u1.readp31() in let sum1 = u1.computeSum1(rp11, rp21, rp31) in let rp12 = u2.readp12() in let rp22 = u2.readp22() in let rp32 = u2.readp32() in let sum2 = u2.computeSum2(rp12, rp22, rp32) in let rp13 = u3.readp13() in let rp23 = u3.readp23() in let rp33 = u3.readp33() in let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u1random3outputC, m13H))
s.add(cLe(m13s1C, u1random3input0C))
s.add(cLe(botC, u1random3input0C))
s.add(bLe(u1random3input0I, m13s1I))
s.add(bLe(u1random3input0I, botI))
s.add(bLe(u1random3input0A, m13s1A))
s.add(bLe(u1random3input0A, botA))
s.add(availabilityP(u1random3input0A, u1qc, m13H))
#ThisCallT: this.m12(v13)
s.add(cLe(m13conxtC, m12conxtC))
s.add(bLe(m12conxtI, m13conxtI))
s.add(bLe(m12conxtA, m13conxtA))
s.add(cLe(u1random3outputC, m12v13C))
s.add(cLe(botC, m12v13C))
s.add(bLe(m12v13I, u1random3outputI))
s.add(bLe(m12v13I, botI))
s.add(bLe(m12v13A, u1random3outputA))
s.add(bLe(m12v13A, botA))
s.add(cLe(m13conxtC, m12v13C))
s.add(bLe(m12v13I, m13conxtI))
s.add(bLe(m12v13A, m13conxtA))
s.add(availabilityP(m12v13A, m12Q, m13H))
s.add(cIntegrityE(m13s1I, m13Q))
s.add(cLeH(m13s1C, m13H))
s.add(cLeH(m13conxtC, m13H))
#MethodT: m12
#ObjCallT: let x19 = u1.writep13(v13) in let rp11 = u1.readp11() in let rp21 = u1.readp21() in let rp31 = u1.readp31() in let sum1 = u1.computeSum1(rp11, rp21, rp31) in let rp12 = u2.readp12() in let rp22 = u2.readp22() in let rp32 = u2.readp32() in let sum2 = u2.computeSum2(rp12, rp22, rp32) in let rp13 = u3.readp13() in let rp23 = u3.readp23() in let rp33 = u3.readp33() in let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u1writep13outputC, m12H))
s.add(cLe(m12v13C, u1writep13input0C))
s.add(cLe(botC, u1writep13input0C))
s.add(bLe(u1writep13input0I, m12v13I))
s.add(bLe(u1writep13input0I, botI))
s.add(bLe(u1writep13input0A, m12v13A))
s.add(bLe(u1writep13input0A, botA))
s.add(availabilityP(u1writep13input0A, u1qc, m12H))
#ThisCallT: this.m11()
s.add(cLe(m12conxtC, m11conxtC))
s.add(bLe(m11conxtI, m12conxtI))
s.add(bLe(m11conxtA, m12conxtA))
s.add(availabilityP(m11botA, m11Q, m12H))
s.add(cLe(m12conxtC, m11botC))
s.add(bLe(m11botI, m12conxtI))
s.add(bLe(m11botA, m12conxtA))
s.add(cIntegrityE(m12v13I, m12Q))
s.add(cLeH(m12v13C, m12H))
s.add(cLeH(m12conxtC, m12H))
#MethodT: m11
m11botC = m11conxtC
m11botI = m11conxtI
m11botA = m11conxtA
#ObjCallT: let rp11 = u1.readp11() in let rp21 = u1.readp21() in let rp31 = u1.readp31() in let sum1 = u1.computeSum1(rp11, rp21, rp31) in let rp12 = u2.readp12() in let rp22 = u2.readp22() in let rp32 = u2.readp32() in let sum2 = u2.computeSum2(rp12, rp22, rp32) in let rp13 = u3.readp13() in let rp23 = u3.readp23() in let rp33 = u3.readp33() in let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u1readp11outputC, m11H))
s.add(availabilityP(u1readp11botA, u1qc, m11H))
#ThisCallT: this.m10(rp11)
s.add(cLe(m11conxtC, m10conxtC))
s.add(bLe(m10conxtI, m11conxtI))
s.add(bLe(m10conxtA, m11conxtA))
s.add(cLe(u1readp11outputC, m10rp11C))
s.add(cLe(botC, m10rp11C))
s.add(bLe(m10rp11I, u1readp11outputI))
s.add(bLe(m10rp11I, botI))
s.add(bLe(m10rp11A, u1readp11outputA))
s.add(bLe(m10rp11A, botA))
s.add(cLe(m11conxtC, m10rp11C))
s.add(bLe(m10rp11I, m11conxtI))
s.add(bLe(m10rp11A, m11conxtA))
s.add(availabilityP(m10rp11A, m10Q, m11H))
s.add(cIntegrityE(m11botI, m11Q))
s.add(cLeH(m11botC, m11H))
s.add(cLeH(m11conxtC, m11H))
#MethodT: m10
#ObjCallT: let rp21 = u1.readp21() in let rp31 = u1.readp31() in let sum1 = u1.computeSum1(rp11, rp21, rp31) in let rp12 = u2.readp12() in let rp22 = u2.readp22() in let rp32 = u2.readp32() in let sum2 = u2.computeSum2(rp12, rp22, rp32) in let rp13 = u3.readp13() in let rp23 = u3.readp23() in let rp33 = u3.readp33() in let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u1readp21outputC, m10H))
s.add(availabilityP(u1readp21botA, u1qc, m10H))
#ThisCallT: this.m9(rp21, rp11)
s.add(cLe(m10conxtC, m9conxtC))
s.add(bLe(m9conxtI, m10conxtI))
s.add(bLe(m9conxtA, m10conxtA))
s.add(cLe(u1readp21outputC, m9rp21C))
s.add(cLe(botC, m9rp21C))
s.add(bLe(m9rp21I, u1readp21outputI))
s.add(bLe(m9rp21I, botI))
s.add(bLe(m9rp21A, u1readp21outputA))
s.add(bLe(m9rp21A, botA))
s.add(cLe(m10conxtC, m9rp21C))
s.add(bLe(m9rp21I, m10conxtI))
s.add(bLe(m9rp21A, m10conxtA))
s.add(availabilityP(m9rp21A, m9Q, m10H))
s.add(cLe(m10rp11C, m9rp11C))
s.add(cLe(botC, m9rp11C))
s.add(bLe(m9rp11I, m10rp11I))
s.add(bLe(m9rp11I, botI))
s.add(bLe(m9rp11A, m10rp11A))
s.add(bLe(m9rp11A, botA))
s.add(cLe(m10conxtC, m9rp11C))
s.add(bLe(m9rp11I, m10conxtI))
s.add(bLe(m9rp11A, m10conxtA))
s.add(availabilityP(m9rp11A, m9Q, m10H))
s.add(cIntegrityE(m10rp11I, m10Q))
s.add(cLeH(m10rp11C, m10H))
s.add(cLeH(m10conxtC, m10H))
#MethodT: m9
#ObjCallT: let rp31 = u1.readp31() in let sum1 = u1.computeSum1(rp11, rp21, rp31) in let rp12 = u2.readp12() in let rp22 = u2.readp22() in let rp32 = u2.readp32() in let sum2 = u2.computeSum2(rp12, rp22, rp32) in let rp13 = u3.readp13() in let rp23 = u3.readp23() in let rp33 = u3.readp33() in let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u1readp31outputC, m9H))
s.add(availabilityP(u1readp31botA, u1qc, m9H))
#ThisCallT: this.m8(rp21, rp31, rp11)
s.add(cLe(m9conxtC, m8conxtC))
s.add(bLe(m8conxtI, m9conxtI))
s.add(bLe(m8conxtA, m9conxtA))
s.add(cLe(m9rp21C, m8rp21C))
s.add(cLe(botC, m8rp21C))
s.add(bLe(m8rp21I, m9rp21I))
s.add(bLe(m8rp21I, botI))
s.add(bLe(m8rp21A, m9rp21A))
s.add(bLe(m8rp21A, botA))
s.add(cLe(m9conxtC, m8rp21C))
s.add(bLe(m8rp21I, m9conxtI))
s.add(bLe(m8rp21A, m9conxtA))
s.add(availabilityP(m8rp21A, m8Q, m9H))
s.add(cLe(u1readp31outputC, m8rp31C))
s.add(cLe(botC, m8rp31C))
s.add(bLe(m8rp31I, u1readp31outputI))
s.add(bLe(m8rp31I, botI))
s.add(bLe(m8rp31A, u1readp31outputA))
s.add(bLe(m8rp31A, botA))
s.add(cLe(m9conxtC, m8rp31C))
s.add(bLe(m8rp31I, m9conxtI))
s.add(bLe(m8rp31A, m9conxtA))
s.add(availabilityP(m8rp31A, m8Q, m9H))
s.add(cLe(m9rp11C, m8rp11C))
s.add(cLe(botC, m8rp11C))
s.add(bLe(m8rp11I, m9rp11I))
s.add(bLe(m8rp11I, botI))
s.add(bLe(m8rp11A, m9rp11A))
s.add(bLe(m8rp11A, botA))
s.add(cLe(m9conxtC, m8rp11C))
s.add(bLe(m8rp11I, m9conxtI))
s.add(bLe(m8rp11A, m9conxtA))
s.add(availabilityP(m8rp11A, m8Q, m9H))
s.add(cIntegrityE(m9rp21I, m9Q))
s.add(cIntegrityE(m9rp11I, m9Q))
s.add(cLeH(m9rp21C, m9H))
s.add(cLeH(m9rp11C, m9H))
s.add(cLeH(m9conxtC, m9H))
#MethodT: m8
#ObjCallT: let sum1 = u1.computeSum1(rp11, rp21, rp31) in let rp12 = u2.readp12() in let rp22 = u2.readp22() in let rp32 = u2.readp32() in let sum2 = u2.computeSum2(rp12, rp22, rp32) in let rp13 = u3.readp13() in let rp23 = u3.readp23() in let rp33 = u3.readp33() in let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u1computeSum1outputC, m8H))
s.add(cLe(m8rp11C, u1computeSum1input0C))
s.add(cLe(botC, u1computeSum1input0C))
s.add(bLe(u1computeSum1input0I, m8rp11I))
s.add(bLe(u1computeSum1input0I, botI))
s.add(bLe(u1computeSum1input0A, m8rp11A))
s.add(bLe(u1computeSum1input0A, botA))
s.add(availabilityP(u1computeSum1input0A, u1qc, m8H))
s.add(cLe(m8rp21C, u1computeSum1input1C))
s.add(cLe(botC, u1computeSum1input1C))
s.add(bLe(u1computeSum1input1I, m8rp21I))
s.add(bLe(u1computeSum1input1I, botI))
s.add(bLe(u1computeSum1input1A, m8rp21A))
s.add(bLe(u1computeSum1input1A, botA))
s.add(availabilityP(u1computeSum1input1A, u1qc, m8H))
s.add(cLe(m8rp31C, u1computeSum1input2C))
s.add(cLe(botC, u1computeSum1input2C))
s.add(bLe(u1computeSum1input2I, m8rp31I))
s.add(bLe(u1computeSum1input2I, botI))
s.add(bLe(u1computeSum1input2A, m8rp31A))
s.add(bLe(u1computeSum1input2A, botA))
s.add(availabilityP(u1computeSum1input2A, u1qc, m8H))
#ThisCallT: this.m7(sum1)
s.add(cLe(m8conxtC, m7conxtC))
s.add(bLe(m7conxtI, m8conxtI))
s.add(bLe(m7conxtA, m8conxtA))
s.add(cLe(u1computeSum1outputC, m7sum1C))
s.add(cLe(botC, m7sum1C))
s.add(bLe(m7sum1I, u1computeSum1outputI))
s.add(bLe(m7sum1I, botI))
s.add(bLe(m7sum1A, u1computeSum1outputA))
s.add(bLe(m7sum1A, botA))
s.add(cLe(m8conxtC, m7sum1C))
s.add(bLe(m7sum1I, m8conxtI))
s.add(bLe(m7sum1A, m8conxtA))
s.add(availabilityP(m7sum1A, m7Q, m8H))
s.add(cIntegrityE(m8rp21I, m8Q))
s.add(cIntegrityE(m8rp31I, m8Q))
s.add(cIntegrityE(m8rp11I, m8Q))
s.add(cLeH(m8rp21C, m8H))
s.add(cLeH(m8rp31C, m8H))
s.add(cLeH(m8rp11C, m8H))
s.add(cLeH(m8conxtC, m8H))
#MethodT: m7
#ObjCallT: let rp12 = u2.readp12() in let rp22 = u2.readp22() in let rp32 = u2.readp32() in let sum2 = u2.computeSum2(rp12, rp22, rp32) in let rp13 = u3.readp13() in let rp23 = u3.readp23() in let rp33 = u3.readp33() in let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u2readp12outputC, m7H))
s.add(availabilityP(u2readp12botA, u2qc, m7H))
#ThisCallT: this.m6(sum1, rp12)
s.add(cLe(m7conxtC, m6conxtC))
s.add(bLe(m6conxtI, m7conxtI))
s.add(bLe(m6conxtA, m7conxtA))
s.add(cLe(m7sum1C, m6sum1C))
s.add(cLe(botC, m6sum1C))
s.add(bLe(m6sum1I, m7sum1I))
s.add(bLe(m6sum1I, botI))
s.add(bLe(m6sum1A, m7sum1A))
s.add(bLe(m6sum1A, botA))
s.add(cLe(m7conxtC, m6sum1C))
s.add(bLe(m6sum1I, m7conxtI))
s.add(bLe(m6sum1A, m7conxtA))
s.add(availabilityP(m6sum1A, m6Q, m7H))
s.add(cLe(u2readp12outputC, m6rp12C))
s.add(cLe(botC, m6rp12C))
s.add(bLe(m6rp12I, u2readp12outputI))
s.add(bLe(m6rp12I, botI))
s.add(bLe(m6rp12A, u2readp12outputA))
s.add(bLe(m6rp12A, botA))
s.add(cLe(m7conxtC, m6rp12C))
s.add(bLe(m6rp12I, m7conxtI))
s.add(bLe(m6rp12A, m7conxtA))
s.add(availabilityP(m6rp12A, m6Q, m7H))
s.add(cIntegrityE(m7sum1I, m7Q))
s.add(cLeH(m7sum1C, m7H))
s.add(cLeH(m7conxtC, m7H))
#MethodT: m6
#ObjCallT: let rp22 = u2.readp22() in let rp32 = u2.readp32() in let sum2 = u2.computeSum2(rp12, rp22, rp32) in let rp13 = u3.readp13() in let rp23 = u3.readp23() in let rp33 = u3.readp33() in let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u2readp22outputC, m6H))
s.add(availabilityP(u2readp22botA, u2qc, m6H))
#ThisCallT: this.m5(sum1, rp12, rp22)
s.add(cLe(m6conxtC, m5conxtC))
s.add(bLe(m5conxtI, m6conxtI))
s.add(bLe(m5conxtA, m6conxtA))
s.add(cLe(m6sum1C, m5sum1C))
s.add(cLe(botC, m5sum1C))
s.add(bLe(m5sum1I, m6sum1I))
s.add(bLe(m5sum1I, botI))
s.add(bLe(m5sum1A, m6sum1A))
s.add(bLe(m5sum1A, botA))
s.add(cLe(m6conxtC, m5sum1C))
s.add(bLe(m5sum1I, m6conxtI))
s.add(bLe(m5sum1A, m6conxtA))
s.add(availabilityP(m5sum1A, m5Q, m6H))
s.add(cLe(m6rp12C, m5rp12C))
s.add(cLe(botC, m5rp12C))
s.add(bLe(m5rp12I, m6rp12I))
s.add(bLe(m5rp12I, botI))
s.add(bLe(m5rp12A, m6rp12A))
s.add(bLe(m5rp12A, botA))
s.add(cLe(m6conxtC, m5rp12C))
s.add(bLe(m5rp12I, m6conxtI))
s.add(bLe(m5rp12A, m6conxtA))
s.add(availabilityP(m5rp12A, m5Q, m6H))
s.add(cLe(u2readp22outputC, m5rp22C))
s.add(cLe(botC, m5rp22C))
s.add(bLe(m5rp22I, u2readp22outputI))
s.add(bLe(m5rp22I, botI))
s.add(bLe(m5rp22A, u2readp22outputA))
s.add(bLe(m5rp22A, botA))
s.add(cLe(m6conxtC, m5rp22C))
s.add(bLe(m5rp22I, m6conxtI))
s.add(bLe(m5rp22A, m6conxtA))
s.add(availabilityP(m5rp22A, m5Q, m6H))
s.add(cIntegrityE(m6sum1I, m6Q))
s.add(cIntegrityE(m6rp12I, m6Q))
s.add(cLeH(m6sum1C, m6H))
s.add(cLeH(m6rp12C, m6H))
s.add(cLeH(m6conxtC, m6H))
#MethodT: m5
#ObjCallT: let rp32 = u2.readp32() in let sum2 = u2.computeSum2(rp12, rp22, rp32) in let rp13 = u3.readp13() in let rp23 = u3.readp23() in let rp33 = u3.readp33() in let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u2readp32outputC, m5H))
s.add(availabilityP(u2readp32botA, u2qc, m5H))
#ThisCallT: this.m4(sum1, rp32, rp12, rp22)
s.add(cLe(m5conxtC, m4conxtC))
s.add(bLe(m4conxtI, m5conxtI))
s.add(bLe(m4conxtA, m5conxtA))
s.add(cLe(m5sum1C, m4sum1C))
s.add(cLe(botC, m4sum1C))
s.add(bLe(m4sum1I, m5sum1I))
s.add(bLe(m4sum1I, botI))
s.add(bLe(m4sum1A, m5sum1A))
s.add(bLe(m4sum1A, botA))
s.add(cLe(m5conxtC, m4sum1C))
s.add(bLe(m4sum1I, m5conxtI))
s.add(bLe(m4sum1A, m5conxtA))
s.add(availabilityP(m4sum1A, m4Q, m5H))
s.add(cLe(u2readp32outputC, m4rp32C))
s.add(cLe(botC, m4rp32C))
s.add(bLe(m4rp32I, u2readp32outputI))
s.add(bLe(m4rp32I, botI))
s.add(bLe(m4rp32A, u2readp32outputA))
s.add(bLe(m4rp32A, botA))
s.add(cLe(m5conxtC, m4rp32C))
s.add(bLe(m4rp32I, m5conxtI))
s.add(bLe(m4rp32A, m5conxtA))
s.add(availabilityP(m4rp32A, m4Q, m5H))
s.add(cLe(m5rp12C, m4rp12C))
s.add(cLe(botC, m4rp12C))
s.add(bLe(m4rp12I, m5rp12I))
s.add(bLe(m4rp12I, botI))
s.add(bLe(m4rp12A, m5rp12A))
s.add(bLe(m4rp12A, botA))
s.add(cLe(m5conxtC, m4rp12C))
s.add(bLe(m4rp12I, m5conxtI))
s.add(bLe(m4rp12A, m5conxtA))
s.add(availabilityP(m4rp12A, m4Q, m5H))
s.add(cLe(m5rp22C, m4rp22C))
s.add(cLe(botC, m4rp22C))
s.add(bLe(m4rp22I, m5rp22I))
s.add(bLe(m4rp22I, botI))
s.add(bLe(m4rp22A, m5rp22A))
s.add(bLe(m4rp22A, botA))
s.add(cLe(m5conxtC, m4rp22C))
s.add(bLe(m4rp22I, m5conxtI))
s.add(bLe(m4rp22A, m5conxtA))
s.add(availabilityP(m4rp22A, m4Q, m5H))
s.add(cIntegrityE(m5sum1I, m5Q))
s.add(cIntegrityE(m5rp12I, m5Q))
s.add(cIntegrityE(m5rp22I, m5Q))
s.add(cLeH(m5sum1C, m5H))
s.add(cLeH(m5rp12C, m5H))
s.add(cLeH(m5rp22C, m5H))
s.add(cLeH(m5conxtC, m5H))
#MethodT: m4
#ObjCallT: let sum2 = u2.computeSum2(rp12, rp22, rp32) in let rp13 = u3.readp13() in let rp23 = u3.readp23() in let rp33 = u3.readp33() in let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u2computeSum2outputC, m4H))
s.add(cLe(m4rp12C, u2computeSum2input0C))
s.add(cLe(botC, u2computeSum2input0C))
s.add(bLe(u2computeSum2input0I, m4rp12I))
s.add(bLe(u2computeSum2input0I, botI))
s.add(bLe(u2computeSum2input0A, m4rp12A))
s.add(bLe(u2computeSum2input0A, botA))
s.add(availabilityP(u2computeSum2input0A, u2qc, m4H))
s.add(cLe(m4rp22C, u2computeSum2input1C))
s.add(cLe(botC, u2computeSum2input1C))
s.add(bLe(u2computeSum2input1I, m4rp22I))
s.add(bLe(u2computeSum2input1I, botI))
s.add(bLe(u2computeSum2input1A, m4rp22A))
s.add(bLe(u2computeSum2input1A, botA))
s.add(availabilityP(u2computeSum2input1A, u2qc, m4H))
s.add(cLe(m4rp32C, u2computeSum2input2C))
s.add(cLe(botC, u2computeSum2input2C))
s.add(bLe(u2computeSum2input2I, m4rp32I))
s.add(bLe(u2computeSum2input2I, botI))
s.add(bLe(u2computeSum2input2A, m4rp32A))
s.add(bLe(u2computeSum2input2A, botA))
s.add(availabilityP(u2computeSum2input2A, u2qc, m4H))
#ThisCallT: this.m3(sum1, sum2)
s.add(cLe(m4conxtC, m3conxtC))
s.add(bLe(m3conxtI, m4conxtI))
s.add(bLe(m3conxtA, m4conxtA))
s.add(cLe(m4sum1C, m3sum1C))
s.add(cLe(botC, m3sum1C))
s.add(bLe(m3sum1I, m4sum1I))
s.add(bLe(m3sum1I, botI))
s.add(bLe(m3sum1A, m4sum1A))
s.add(bLe(m3sum1A, botA))
s.add(cLe(m4conxtC, m3sum1C))
s.add(bLe(m3sum1I, m4conxtI))
s.add(bLe(m3sum1A, m4conxtA))
s.add(availabilityP(m3sum1A, m3Q, m4H))
s.add(cLe(u2computeSum2outputC, m3sum2C))
s.add(cLe(botC, m3sum2C))
s.add(bLe(m3sum2I, u2computeSum2outputI))
s.add(bLe(m3sum2I, botI))
s.add(bLe(m3sum2A, u2computeSum2outputA))
s.add(bLe(m3sum2A, botA))
s.add(cLe(m4conxtC, m3sum2C))
s.add(bLe(m3sum2I, m4conxtI))
s.add(bLe(m3sum2A, m4conxtA))
s.add(availabilityP(m3sum2A, m3Q, m4H))
s.add(cIntegrityE(m4sum1I, m4Q))
s.add(cIntegrityE(m4rp32I, m4Q))
s.add(cIntegrityE(m4rp12I, m4Q))
s.add(cIntegrityE(m4rp22I, m4Q))
s.add(cLeH(m4sum1C, m4H))
s.add(cLeH(m4rp32C, m4H))
s.add(cLeH(m4rp12C, m4H))
s.add(cLeH(m4rp22C, m4H))
s.add(cLeH(m4conxtC, m4H))
#MethodT: m3
#ObjCallT: let rp13 = u3.readp13() in let rp23 = u3.readp23() in let rp33 = u3.readp33() in let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u3readp13outputC, m3H))
s.add(availabilityP(u3readp13botA, u3qc, m3H))
#ThisCallT: this.m2(sum1, sum2, rp13)
s.add(cLe(m3conxtC, m2conxtC))
s.add(bLe(m2conxtI, m3conxtI))
s.add(bLe(m2conxtA, m3conxtA))
s.add(cLe(m3sum1C, m2sum1C))
s.add(cLe(botC, m2sum1C))
s.add(bLe(m2sum1I, m3sum1I))
s.add(bLe(m2sum1I, botI))
s.add(bLe(m2sum1A, m3sum1A))
s.add(bLe(m2sum1A, botA))
s.add(cLe(m3conxtC, m2sum1C))
s.add(bLe(m2sum1I, m3conxtI))
s.add(bLe(m2sum1A, m3conxtA))
s.add(availabilityP(m2sum1A, m2Q, m3H))
s.add(cLe(m3sum2C, m2sum2C))
s.add(cLe(botC, m2sum2C))
s.add(bLe(m2sum2I, m3sum2I))
s.add(bLe(m2sum2I, botI))
s.add(bLe(m2sum2A, m3sum2A))
s.add(bLe(m2sum2A, botA))
s.add(cLe(m3conxtC, m2sum2C))
s.add(bLe(m2sum2I, m3conxtI))
s.add(bLe(m2sum2A, m3conxtA))
s.add(availabilityP(m2sum2A, m2Q, m3H))
s.add(cLe(u3readp13outputC, m2rp13C))
s.add(cLe(botC, m2rp13C))
s.add(bLe(m2rp13I, u3readp13outputI))
s.add(bLe(m2rp13I, botI))
s.add(bLe(m2rp13A, u3readp13outputA))
s.add(bLe(m2rp13A, botA))
s.add(cLe(m3conxtC, m2rp13C))
s.add(bLe(m2rp13I, m3conxtI))
s.add(bLe(m2rp13A, m3conxtA))
s.add(availabilityP(m2rp13A, m2Q, m3H))
s.add(cIntegrityE(m3sum1I, m3Q))
s.add(cIntegrityE(m3sum2I, m3Q))
s.add(cLeH(m3sum1C, m3H))
s.add(cLeH(m3sum2C, m3H))
s.add(cLeH(m3conxtC, m3H))
#MethodT: m2
#ObjCallT: let rp23 = u3.readp23() in let rp33 = u3.readp33() in let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u3readp23outputC, m2H))
s.add(availabilityP(u3readp23botA, u3qc, m2H))
#ThisCallT: this.m1(sum1, sum2, rp13, rp23)
s.add(cLe(m2conxtC, m1conxtC))
s.add(bLe(m1conxtI, m2conxtI))
s.add(bLe(m1conxtA, m2conxtA))
s.add(cLe(m2sum1C, m1sum1C))
s.add(cLe(botC, m1sum1C))
s.add(bLe(m1sum1I, m2sum1I))
s.add(bLe(m1sum1I, botI))
s.add(bLe(m1sum1A, m2sum1A))
s.add(bLe(m1sum1A, botA))
s.add(cLe(m2conxtC, m1sum1C))
s.add(bLe(m1sum1I, m2conxtI))
s.add(bLe(m1sum1A, m2conxtA))
s.add(availabilityP(m1sum1A, m1Q, m2H))
s.add(cLe(m2sum2C, m1sum2C))
s.add(cLe(botC, m1sum2C))
s.add(bLe(m1sum2I, m2sum2I))
s.add(bLe(m1sum2I, botI))
s.add(bLe(m1sum2A, m2sum2A))
s.add(bLe(m1sum2A, botA))
s.add(cLe(m2conxtC, m1sum2C))
s.add(bLe(m1sum2I, m2conxtI))
s.add(bLe(m1sum2A, m2conxtA))
s.add(availabilityP(m1sum2A, m1Q, m2H))
s.add(cLe(m2rp13C, m1rp13C))
s.add(cLe(botC, m1rp13C))
s.add(bLe(m1rp13I, m2rp13I))
s.add(bLe(m1rp13I, botI))
s.add(bLe(m1rp13A, m2rp13A))
s.add(bLe(m1rp13A, botA))
s.add(cLe(m2conxtC, m1rp13C))
s.add(bLe(m1rp13I, m2conxtI))
s.add(bLe(m1rp13A, m2conxtA))
s.add(availabilityP(m1rp13A, m1Q, m2H))
s.add(cLe(u3readp23outputC, m1rp23C))
s.add(cLe(botC, m1rp23C))
s.add(bLe(m1rp23I, u3readp23outputI))
s.add(bLe(m1rp23I, botI))
s.add(bLe(m1rp23A, u3readp23outputA))
s.add(bLe(m1rp23A, botA))
s.add(cLe(m2conxtC, m1rp23C))
s.add(bLe(m1rp23I, m2conxtI))
s.add(bLe(m1rp23A, m2conxtA))
s.add(availabilityP(m1rp23A, m1Q, m2H))
s.add(cIntegrityE(m2sum1I, m2Q))
s.add(cIntegrityE(m2sum2I, m2Q))
s.add(cIntegrityE(m2rp13I, m2Q))
s.add(cLeH(m2sum1C, m2H))
s.add(cLeH(m2sum2C, m2H))
s.add(cLeH(m2rp13C, m2H))
s.add(cLeH(m2conxtC, m2H))
#MethodT: m1
#ObjCallT: let rp33 = u3.readp33() in let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u3readp33outputC, m1H))
s.add(availabilityP(u3readp33botA, u3qc, m1H))
#ThisCallT: this.m0(sum1, sum2, rp13, rp23, rp33)
s.add(cLe(m1conxtC, m0conxtC))
s.add(bLe(m0conxtI, m1conxtI))
s.add(bLe(m0conxtA, m1conxtA))
s.add(cLe(m1sum1C, m0sum1C))
s.add(cLe(botC, m0sum1C))
s.add(bLe(m0sum1I, m1sum1I))
s.add(bLe(m0sum1I, botI))
s.add(bLe(m0sum1A, m1sum1A))
s.add(bLe(m0sum1A, botA))
s.add(cLe(m1conxtC, m0sum1C))
s.add(bLe(m0sum1I, m1conxtI))
s.add(bLe(m0sum1A, m1conxtA))
s.add(availabilityP(m0sum1A, m0Q, m1H))
s.add(cLe(m1sum2C, m0sum2C))
s.add(cLe(botC, m0sum2C))
s.add(bLe(m0sum2I, m1sum2I))
s.add(bLe(m0sum2I, botI))
s.add(bLe(m0sum2A, m1sum2A))
s.add(bLe(m0sum2A, botA))
s.add(cLe(m1conxtC, m0sum2C))
s.add(bLe(m0sum2I, m1conxtI))
s.add(bLe(m0sum2A, m1conxtA))
s.add(availabilityP(m0sum2A, m0Q, m1H))
s.add(cLe(m1rp13C, m0rp13C))
s.add(cLe(botC, m0rp13C))
s.add(bLe(m0rp13I, m1rp13I))
s.add(bLe(m0rp13I, botI))
s.add(bLe(m0rp13A, m1rp13A))
s.add(bLe(m0rp13A, botA))
s.add(cLe(m1conxtC, m0rp13C))
s.add(bLe(m0rp13I, m1conxtI))
s.add(bLe(m0rp13A, m1conxtA))
s.add(availabilityP(m0rp13A, m0Q, m1H))
s.add(cLe(m1rp23C, m0rp23C))
s.add(cLe(botC, m0rp23C))
s.add(bLe(m0rp23I, m1rp23I))
s.add(bLe(m0rp23I, botI))
s.add(bLe(m0rp23A, m1rp23A))
s.add(bLe(m0rp23A, botA))
s.add(cLe(m1conxtC, m0rp23C))
s.add(bLe(m0rp23I, m1conxtI))
s.add(bLe(m0rp23A, m1conxtA))
s.add(availabilityP(m0rp23A, m0Q, m1H))
s.add(cLe(u3readp33outputC, m0rp33C))
s.add(cLe(botC, m0rp33C))
s.add(bLe(m0rp33I, u3readp33outputI))
s.add(bLe(m0rp33I, botI))
s.add(bLe(m0rp33A, u3readp33outputA))
s.add(bLe(m0rp33A, botA))
s.add(cLe(m1conxtC, m0rp33C))
s.add(bLe(m0rp33I, m1conxtI))
s.add(bLe(m0rp33A, m1conxtA))
s.add(availabilityP(m0rp33A, m0Q, m1H))
s.add(cIntegrityE(m1sum1I, m1Q))
s.add(cIntegrityE(m1sum2I, m1Q))
s.add(cIntegrityE(m1rp13I, m1Q))
s.add(cIntegrityE(m1rp23I, m1Q))
s.add(cLeH(m1sum1C, m1H))
s.add(cLeH(m1sum2C, m1H))
s.add(cLeH(m1rp13C, m1H))
s.add(cLeH(m1rp23C, m1H))
s.add(cLeH(m1conxtC, m1H))
#MethodT: m0
#ObjCallT: let sum3 = u3.computeSum3(rp13, rp23, rp33) in this.ret(((sum1 + sum2) + sum3))
s.add(cLeH(u3computeSum3outputC, m0H))
s.add(cLe(m0rp13C, u3computeSum3input0C))
s.add(cLe(botC, u3computeSum3input0C))
s.add(bLe(u3computeSum3input0I, m0rp13I))
s.add(bLe(u3computeSum3input0I, botI))
s.add(bLe(u3computeSum3input0A, m0rp13A))
s.add(bLe(u3computeSum3input0A, botA))
s.add(availabilityP(u3computeSum3input0A, u3qc, m0H))
s.add(cLe(m0rp23C, u3computeSum3input1C))
s.add(cLe(botC, u3computeSum3input1C))
s.add(bLe(u3computeSum3input1I, m0rp23I))
s.add(bLe(u3computeSum3input1I, botI))
s.add(bLe(u3computeSum3input1A, m0rp23A))
s.add(bLe(u3computeSum3input1A, botA))
s.add(availabilityP(u3computeSum3input1A, u3qc, m0H))
s.add(cLe(m0rp33C, u3computeSum3input2C))
s.add(cLe(botC, u3computeSum3input2C))
s.add(bLe(u3computeSum3input2I, m0rp33I))
s.add(bLe(u3computeSum3input2I, botI))
s.add(bLe(u3computeSum3input2A, m0rp33A))
s.add(bLe(u3computeSum3input2A, botA))
s.add(availabilityP(u3computeSum3input2A, u3qc, m0H))
#ThisCallT: this.ret(((sum1 + sum2) + sum3))
s.add(cLe(m0conxtC, resultC))
s.add(bLe(resultI, m0conxtI))
s.add(bLe(resultA, m0conxtA))
s.add(cLe(m0sum1C, resultC))
s.add(cLe(botC, resultC))
s.add(cLe(m0sum2C, resultC))
s.add(cLe(botC, resultC))
s.add(cLe(u3computeSum3outputC, resultC))
s.add(cLe(botC, resultC))
s.add(bLe(resultI, m0sum1I))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, m0sum2I))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, u3computeSum3outputI))
s.add(bLe(resultI, botI))
s.add(bLe(resultA, m0sum1A))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, m0sum2A))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, u3computeSum3outputA))
s.add(bLe(resultA, botA))
s.add(cLe(m0conxtC, resultC))
s.add(bLe(resultI, m0conxtI))
s.add(bLe(resultA, m0conxtA))
s.add(availabilityP(resultA, resQ, m0H))
s.add(cIntegrityE(m0sum1I, m0Q))
s.add(cIntegrityE(m0sum2I, m0Q))
s.add(cIntegrityE(m0rp13I, m0Q))
s.add(cIntegrityE(m0rp23I, m0Q))
s.add(cIntegrityE(m0rp33I, m0Q))
s.add(cLeH(m0sum1C, m0H))
s.add(cLeH(m0sum2C, m0H))
s.add(cLeH(m0rp13C, m0H))
s.add(cLeH(m0rp23C, m0H))
s.add(cLeH(m0rp33C, m0H))
s.add(cLeH(m0conxtC, m0H))
#MethodT: ret
s.add(cLeH(resultC, resH))
s.add(cIntegrityE(resultI, resQ))
s.add(cLe(startC, m18conxtC))
s.add(bLe(m18conxtI, startI))
s.add(bLe(m18conxtA, startA))
s.add(availabilityP(m18botA, m18Q, resH))
s.add(cLe(startC, m18botC))
s.add(bLe(m18botI, startI))
s.add(bLe(m18botA, startA))
weight = [ 1, 1, 1]

s.minimize(sum(m0H[i] * weight[i] for i in range(n)) + sum(m1H[i] * weight[i] for i in range(n)) + sum(m2H[i] * weight[i] for i in range(n)) + sum(m3H[i] * weight[i] for i in range(n)) + sum(m4H[i] * weight[i] for i in range(n)) + sum(m5H[i] * weight[i] for i in range(n)) + sum(m6H[i] * weight[i] for i in range(n)) + sum(m7H[i] * weight[i] for i in range(n)) + sum(m8H[i] * weight[i] for i in range(n)) + sum(m9H[i] * weight[i] for i in range(n)) + sum(m11H[i] * weight[i] for i in range(n)) + sum(m10H[i] * weight[i] for i in range(n)) + sum(m13H[i] * weight[i] for i in range(n)) + sum(m12H[i] * weight[i] for i in range(n)) + sum(m15H[i] * weight[i] for i in range(n)) + sum(m14H[i] * weight[i] for i in range(n)) + sum(m17H[i] * weight[i] for i in range(n)) + sum(m16H[i] * weight[i] for i in range(n)) + sum(m18H[i] * weight[i] for i in range(n)) + sum(u1qs[0][i] * weight[i] for i in range(n)) + sum(u1qs[1][i] * weight[i] for i in range(n)) + sum(u1qs[2][i] * weight[i] for i in range(n)) + sum(u2qs[0][i] * weight[i] for i in range(n)) + sum(u2qs[1][i] * weight[i] for i in range(n)) + sum(u2qs[2][i] * weight[i] for i in range(n)) + sum(u3qs[0][i] * weight[i] for i in range(n)) + sum(u3qs[1][i] * weight[i] for i in range(n)) + sum(u3qs[2][i] * weight[i] for i in range(n)) + sum(resQ[0]) + sum(resQ[1]) + sum(resQ[2]) + sum(m0Q[0]) + sum(m0Q[1]) + sum(m0Q[2]) + sum(m1Q[0]) + sum(m1Q[1]) + sum(m1Q[2]) + sum(m2Q[0]) + sum(m2Q[1]) + sum(m2Q[2]) + sum(m3Q[0]) + sum(m3Q[1]) + sum(m3Q[2]) + sum(m4Q[0]) + sum(m4Q[1]) + sum(m4Q[2]) + sum(m5Q[0]) + sum(m5Q[1]) + sum(m5Q[2]) + sum(m6Q[0]) + sum(m6Q[1]) + sum(m6Q[2]) + sum(m7Q[0]) + sum(m7Q[1]) + sum(m7Q[2]) + sum(m8Q[0]) + sum(m8Q[1]) + sum(m8Q[2]) + sum(m9Q[0]) + sum(m9Q[1]) + sum(m9Q[2]) + sum(m11Q[0]) + sum(m11Q[1]) + sum(m11Q[2]) + sum(m10Q[0]) + sum(m10Q[1]) + sum(m10Q[2]) + sum(m13Q[0]) + sum(m13Q[1]) + sum(m13Q[2]) + sum(m12Q[0]) + sum(m12Q[1]) + sum(m12Q[2]) + sum(m15Q[0]) + sum(m15Q[1]) + sum(m15Q[2]) + sum(m14Q[0]) + sum(m14Q[1]) + sum(m14Q[2]) + sum(m17Q[0]) + sum(m17Q[1]) + sum(m17Q[2]) + sum(m16Q[0]) + sum(m16Q[1]) + sum(m16Q[2]) + sum(m18Q[0]) + sum(m18Q[1]) + sum(m18Q[2]))
print(s.check())
m = s.model()
print("resH:")
print(resH)
print("m0H:")
print([m[hInfo].as_long() for hInfo in m0H])
print("m1H:")
print([m[hInfo].as_long() for hInfo in m1H])
print("m2H:")
print([m[hInfo].as_long() for hInfo in m2H])
print("m3H:")
print([m[hInfo].as_long() for hInfo in m3H])
print("m4H:")
print([m[hInfo].as_long() for hInfo in m4H])
print("m5H:")
print([m[hInfo].as_long() for hInfo in m5H])
print("m6H:")
print([m[hInfo].as_long() for hInfo in m6H])
print("m7H:")
print([m[hInfo].as_long() for hInfo in m7H])
print("m8H:")
print([m[hInfo].as_long() for hInfo in m8H])
print("m9H:")
print([m[hInfo].as_long() for hInfo in m9H])
print("m11H:")
print([m[hInfo].as_long() for hInfo in m11H])
print("m10H:")
print([m[hInfo].as_long() for hInfo in m10H])
print("m13H:")
print([m[hInfo].as_long() for hInfo in m13H])
print("m12H:")
print([m[hInfo].as_long() for hInfo in m12H])
print("m15H:")
print([m[hInfo].as_long() for hInfo in m15H])
print("m14H:")
print([m[hInfo].as_long() for hInfo in m14H])
print("m17H:")
print([m[hInfo].as_long() for hInfo in m17H])
print("m16H:")
print([m[hInfo].as_long() for hInfo in m16H])
print("m18H:")
print([m[hInfo].as_long() for hInfo in m18H])
print("resQ:")
print([e for qs in resQ for e in qs])
print([m[e].as_long() for qs in resQ for e in qs])
print("m0Q:")
print([e for qs in m0Q for e in qs])
print([m[e].as_long() for qs in m0Q for e in qs])
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
print("m5Q:")
print([e for qs in m5Q for e in qs])
print([m[e].as_long() for qs in m5Q for e in qs])
print("m6Q:")
print([e for qs in m6Q for e in qs])
print([m[e].as_long() for qs in m6Q for e in qs])
print("m7Q:")
print([e for qs in m7Q for e in qs])
print([m[e].as_long() for qs in m7Q for e in qs])
print("m8Q:")
print([e for qs in m8Q for e in qs])
print([m[e].as_long() for qs in m8Q for e in qs])
print("m9Q:")
print([e for qs in m9Q for e in qs])
print([m[e].as_long() for qs in m9Q for e in qs])
print("m11Q:")
print([e for qs in m11Q for e in qs])
print([m[e].as_long() for qs in m11Q for e in qs])
print("m10Q:")
print([e for qs in m10Q for e in qs])
print([m[e].as_long() for qs in m10Q for e in qs])
print("m13Q:")
print([e for qs in m13Q for e in qs])
print([m[e].as_long() for qs in m13Q for e in qs])
print("m12Q:")
print([e for qs in m12Q for e in qs])
print([m[e].as_long() for qs in m12Q for e in qs])
print("m15Q:")
print([e for qs in m15Q for e in qs])
print([m[e].as_long() for qs in m15Q for e in qs])
print("m14Q:")
print([e for qs in m14Q for e in qs])
print([m[e].as_long() for qs in m14Q for e in qs])
print("m17Q:")
print([e for qs in m17Q for e in qs])
print([m[e].as_long() for qs in m17Q for e in qs])
print("m16Q:")
print([e for qs in m16Q for e in qs])
print([m[e].as_long() for qs in m16Q for e in qs])
print("m18Q:")
print([e for qs in m18Q for e in qs])
print([m[e].as_long() for qs in m18Q for e in qs])
print("u1qs:")
print([e for qs in u1qs for e in qs])
print([m[e].as_long() for qs in u1qs for e in qs])
print("u2qs:")
print([e for qs in u2qs for e in qs])
print([m[e].as_long() for qs in u2qs for e in qs])
print("u3qs:")
print([e for qs in u3qs for e in qs])
print([m[e].as_long() for qs in u3qs for e in qs])
endT = time.time() - startT
print(endT)
