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

n = 4
principals = [4, 4, 7, 4]
startC = [ True, True, True, True ]
startI = [[ 4, 4, 7, 4], [ 0, 0, 0, 0], [ 0, 0, 0, 0], [ 0, 0, 0, 0] ]
startA = [[ 4, 4, 7, 4], [ 0, 0, 0, 0], [ 0, 0, 0, 0], [ 0, 0, 0, 0] ]
botC = [ True, True, True, True ]
botI = [[ 4, 4, 7, 4], [ 0, 0, 0, 0], [ 0, 0, 0, 0], [ 0, 0, 0, 0] ]
botA = [[ 4, 4, 7, 4], [ 0, 0, 0, 0], [ 0, 0, 0, 0], [ 0, 0, 0, 0] ]
resultC = [ True, False, False, False ]
resultI = [[ 1, 1, 2, 1], [ 0, 0, 0, 0], [ 0, 0, 0, 0], [ 0, 0, 0, 0] ]
resultA = [[ 1, 1, 2, 1], [ 0, 0, 0, 0], [ 0, 0, 0, 0], [ 0, 0, 0, 0] ]
resH = [4, 0, 0, 0]
resQ = [ [ Int("resQ_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
s.add([ And(0 <= resQ[i][j]) for i in range(n) for j in range(n) ])
s.add([ And(sLe(resQ[i], principals)) for i in range(n) ])
m0H = [ Int('m0H_%s' % i) for i in range(n) ] 
m0Q = [ [ Int('m0Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0conxtC = [ Bool('m0conxtC_%s' % i) for i in range(n) ]
m0conxtI = [ [ Int('m0conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0conxtA = [ [ Int('m0conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0bcC = [ Bool('m0bcC_%s' % i) for i in range(n) ]
m0bcI = [ [ Int('m0bcI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0bcA = [ [ Int('m0bcA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0mC = [ Bool('m0mC_%s' % i) for i in range(n) ]
m0mI = [ [ Int('m0mI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0mA = [ [ Int('m0mA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0range0 = [ And(0 <= m0conxtI[i][j], 0 <= m0conxtA[i][j], 0 <= m0Q[i][j], 0 <= m0bcI[i][j], 0 <= m0bcA[i][j], 0 <= m0mI[i][j], 0 <= m0mA[i][j]) for i in range(n) for j in range(n) ]
s.add(m0range0)
m0range1 = [And(sLe(m0conxtI[i], principals), sLe(m0conxtA[i], principals), sLe(m0Q[i], principals), sLe(m0bcI[i], principals), sLe(m0bcA[i], principals), sLe(m0mI[i], principals), sLe(m0mA[i], principals)) for i in range(n)]
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
m1mC = [ Bool('m1mC_%s' % i) for i in range(n) ]
m1mI = [ [ Int('m1mI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1mA = [ [ Int('m1mA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1range0 = [ And(0 <= m1conxtI[i][j], 0 <= m1conxtA[i][j], 0 <= m1Q[i][j], 0 <= m1mI[i][j], 0 <= m1mA[i][j]) for i in range(n) for j in range(n) ]
s.add(m1range0)
m1range1 = [And(sLe(m1conxtI[i], principals), sLe(m1conxtA[i], principals), sLe(m1Q[i], principals), sLe(m1mI[i], principals), sLe(m1mA[i], principals)) for i in range(n)]
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
m2newBC = [ Bool('m2newBC_%s' % i) for i in range(n) ]
m2newBI = [ [ Int('m2newBI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2newBA = [ [ Int('m2newBA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2range0 = [ And(0 <= m2conxtI[i][j], 0 <= m2conxtA[i][j], 0 <= m2Q[i][j], 0 <= m2newBI[i][j], 0 <= m2newBA[i][j]) for i in range(n) for j in range(n) ]
s.add(m2range0)
m2range1 = [And(sLe(m2conxtI[i], principals), sLe(m2conxtA[i], principals), sLe(m2Q[i], principals), sLe(m2newBI[i], principals), sLe(m2newBA[i], principals)) for i in range(n)]
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
m3bC = [ Bool('m3bC_%s' % i) for i in range(n) ]
m3bI = [ [ Int('m3bI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3bA = [ [ Int('m3bA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3bLocC = [ Bool('m3bLocC_%s' % i) for i in range(n) ]
m3bLocI = [ [ Int('m3bLocI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3bLocA = [ [ Int('m3bLocA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3range0 = [ And(0 <= m3conxtI[i][j], 0 <= m3conxtA[i][j], 0 <= m3Q[i][j], 0 <= m3bI[i][j], 0 <= m3bA[i][j], 0 <= m3bLocI[i][j], 0 <= m3bLocA[i][j]) for i in range(n) for j in range(n) ]
s.add(m3range0)
m3range1 = [And(sLe(m3conxtI[i], principals), sLe(m3conxtA[i], principals), sLe(m3Q[i], principals), sLe(m3bI[i], principals), sLe(m3bA[i], principals), sLe(m3bLocI[i], principals), sLe(m3bLocA[i], principals)) for i in range(n)]
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
m4bC = [ Bool('m4bC_%s' % i) for i in range(n) ]
m4bI = [ [ Int('m4bI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4bA = [ [ Int('m4bA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m4range0 = [ And(0 <= m4conxtI[i][j], 0 <= m4conxtA[i][j], 0 <= m4Q[i][j], 0 <= m4bI[i][j], 0 <= m4bA[i][j]) for i in range(n) for j in range(n) ]
s.add(m4range0)
m4range1 = [And(sLe(m4conxtI[i], principals), sLe(m4conxtA[i], principals), sLe(m4Q[i], principals), sLe(m4bI[i], principals), sLe(m4bA[i], principals)) for i in range(n)]
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
m5bC = [ Bool('m5bC_%s' % i) for i in range(n) ]
m5bI = [ [ Int('m5bI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5bA = [ [ Int('m5bA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5bIDC = [ Bool('m5bIDC_%s' % i) for i in range(n) ]
m5bIDI = [ [ Int('m5bIDI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5bIDA = [ [ Int('m5bIDA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5aIDC = [ Bool('m5aIDC_%s' % i) for i in range(n) ]
m5aIDI = [ [ Int('m5aIDI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5aIDA = [ [ Int('m5aIDA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m5range0 = [ And(0 <= m5conxtI[i][j], 0 <= m5conxtA[i][j], 0 <= m5Q[i][j], 0 <= m5bI[i][j], 0 <= m5bA[i][j], 0 <= m5bIDI[i][j], 0 <= m5bIDA[i][j], 0 <= m5aIDI[i][j], 0 <= m5aIDA[i][j]) for i in range(n) for j in range(n) ]
s.add(m5range0)
m5range1 = [And(sLe(m5conxtI[i], principals), sLe(m5conxtA[i], principals), sLe(m5Q[i], principals), sLe(m5bI[i], principals), sLe(m5bA[i], principals), sLe(m5bIDI[i], principals), sLe(m5bIDA[i], principals), sLe(m5aIDI[i], principals), sLe(m5aIDA[i], principals)) for i in range(n)]
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
m6bC = [ Bool('m6bC_%s' % i) for i in range(n) ]
m6bI = [ [ Int('m6bI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6bA = [ [ Int('m6bA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6aIDC = [ Bool('m6aIDC_%s' % i) for i in range(n) ]
m6aIDI = [ [ Int('m6aIDI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6aIDA = [ [ Int('m6aIDA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m6range0 = [ And(0 <= m6conxtI[i][j], 0 <= m6conxtA[i][j], 0 <= m6Q[i][j], 0 <= m6bI[i][j], 0 <= m6bA[i][j], 0 <= m6aIDI[i][j], 0 <= m6aIDA[i][j]) for i in range(n) for j in range(n) ]
s.add(m6range0)
m6range1 = [And(sLe(m6conxtI[i], principals), sLe(m6conxtA[i], principals), sLe(m6Q[i], principals), sLe(m6bI[i], principals), sLe(m6bA[i], principals), sLe(m6aIDI[i], principals), sLe(m6aIDA[i], principals)) for i in range(n)]
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
m7bC = [ Bool('m7bC_%s' % i) for i in range(n) ]
m7bI = [ [ Int('m7bI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m7bA = [ [ Int('m7bA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m7range0 = [ And(0 <= m7conxtI[i][j], 0 <= m7conxtA[i][j], 0 <= m7Q[i][j], 0 <= m7bI[i][j], 0 <= m7bA[i][j]) for i in range(n) for j in range(n) ]
s.add(m7range0)
m7range1 = [And(sLe(m7conxtI[i], principals), sLe(m7conxtA[i], principals), sLe(m7Q[i], principals), sLe(m7bI[i], principals), sLe(m7bA[i], principals)) for i in range(n)]
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
m8botC = m8conxtC
m8botI = m8conxtI
m8botA = m8conxtA
m8range0 = [ And(0 <= m8conxtI[i][j], 0 <= m8conxtA[i][j], 0 <= m8Q[i][j], 0 <= m8botI[i][j], 0 <= m8botA[i][j]) for i in range(n) for j in range(n) ]
s.add(m8range0)
m8range1 = [And(sLe(m8conxtI[i], principals), sLe(m8conxtA[i], principals), sLe(m8Q[i], principals), sLe(m8botI[i], principals), sLe(m8botA[i], principals)) for i in range(n)]
s.add(m8range1)
m8range2 = [And(0 <= m8H[i]) for i in range(n)]
s.add(m8range2)
s.add(sLe(m8H, principals))
s.add(Not(nonCheck(m8H)))
s.add(Not(nonCheckQ(m8Q)))
Snappqs = [ [ Int("Snappqs_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
Snappqc = [ [ Int("Snappqc_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
SnappOH = [ Int('SnappOH_%s' % i) for i in range(n) ] 
SnappisFriendinput0C = [ Bool('SnappisFriendinput0C_%s' % i) for i in range(n) ]
SnappisFriendinput0I = [ [ Int("SnappisFriendinput0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
SnappisFriendinput0A = [ [ Int("SnappisFriendinput0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
SnappisFriendinput1C = [ Bool('SnappisFriendinput1C_%s' % i) for i in range(n) ]
SnappisFriendinput1I = [ [ Int("SnappisFriendinput1I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
SnappisFriendinput1A = [ [ Int("SnappisFriendinput1A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
SnappisFriendoutputC = [ True, True, True, True ]
SnappisFriendoutputI = [ [ Int("SnappisFriendoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
SnappisFriendoutputA = [ [ Int("SnappisFriendoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
Snapprange0 = [ And(0 <= Snappqs[i][j], 0 <= Snappqc[i][j], 0 <= SnappisFriendoutputI[i][j], 0 <= SnappisFriendoutputA[i][j], 0 <= SnappisFriendinput0I[i][j], 0 <= SnappisFriendinput0A[i][j], 0 <= SnappisFriendinput1I[i][j], 0 <= SnappisFriendinput1A[i][j]) for i in range(n) for j in range(n) ]
s.add(Snapprange0)
Snapprange1 = [And(sLe(Snappqs[i], principals), sLe(Snappqc[i], principals), sLe(SnappisFriendoutputI[i], principals), sLe(SnappisFriendoutputA[i], principals), sLe(SnappisFriendinput0I[i], principals), sLe(SnappisFriendinput0A[i], principals), sLe(SnappisFriendinput1I[i], principals), sLe(SnappisFriendinput1A[i], principals)) for i in range(n)]
s.add(Snapprange1)
Snapprange2 = [And(0 <= SnappOH[i]) for i in range(n)]
s.add(Snapprange2)
Snapprange3 = sLe(SnappOH, principals)
s.add(Snapprange3)
Bobqs = [ [ Int("Bobqs_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
Bobqc = [ [ Int("Bobqc_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
BobOH = [ Int('BobOH_%s' % i) for i in range(n) ] 
BobcommentbotC = [ Bool('BobcommentbotC_%s' % i) for i in range(n) ]
BobcommentbotI = [ [ Int("BobcommentbotI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
BobcommentbotA = [ [ Int("BobcommentbotA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
BobcommentoutputC = [ True, True, False, False ]
BobcommentoutputI = [ [ Int("BobcommentoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
BobcommentoutputA = [ [ Int("BobcommentoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
BoblocationbotC = [ Bool('BoblocationbotC_%s' % i) for i in range(n) ]
BoblocationbotI = [ [ Int("BoblocationbotI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
BoblocationbotA = [ [ Int("BoblocationbotA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
BoblocationoutputC = [ True, True, True, False ]
BoblocationoutputI = [ [ Int("BoblocationoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
BoblocationoutputA = [ [ Int("BoblocationoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
BobIDbotC = [ Bool('BobIDbotC_%s' % i) for i in range(n) ]
BobIDbotI = [ [ Int("BobIDbotI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
BobIDbotA = [ [ Int("BobIDbotA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
BobIDoutputC = [ True, True, True, True ]
BobIDoutputI = [ [ Int("BobIDoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
BobIDoutputA = [ [ Int("BobIDoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
Bobrange0 = [ And(0 <= Bobqs[i][j], 0 <= Bobqc[i][j], 0 <= BobcommentoutputI[i][j], 0 <= BobcommentoutputA[i][j], 0 <= BobcommentbotI[i][j], 0 <= BobcommentbotA[i][j], 0 <= BoblocationoutputI[i][j], 0 <= BoblocationoutputA[i][j], 0 <= BoblocationbotI[i][j], 0 <= BoblocationbotA[i][j], 0 <= BobIDoutputI[i][j], 0 <= BobIDoutputA[i][j], 0 <= BobIDbotI[i][j], 0 <= BobIDbotA[i][j]) for i in range(n) for j in range(n) ]
s.add(Bobrange0)
Bobrange1 = [And(sLe(Bobqs[i], principals), sLe(Bobqc[i], principals), sLe(BobcommentoutputI[i], principals), sLe(BobcommentoutputA[i], principals), sLe(BobcommentbotI[i], principals), sLe(BobcommentbotA[i], principals), sLe(BoblocationoutputI[i], principals), sLe(BoblocationoutputA[i], principals), sLe(BoblocationbotI[i], principals), sLe(BoblocationbotA[i], principals), sLe(BobIDoutputI[i], principals), sLe(BobIDoutputA[i], principals), sLe(BobIDbotI[i], principals), sLe(BobIDbotA[i], principals)) for i in range(n)]
s.add(Bobrange1)
Bobrange2 = [And(0 <= BobOH[i]) for i in range(n)]
s.add(Bobrange2)
Bobrange3 = sLe(BobOH, principals)
s.add(Bobrange3)
Aliceqs = [ [ Int("Aliceqs_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
Aliceqc = [ [ Int("Aliceqc_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
AliceOH = [ Int('AliceOH_%s' % i) for i in range(n) ] 
AlicenewBoxbotC = [ Bool('AlicenewBoxbotC_%s' % i) for i in range(n) ]
AlicenewBoxbotI = [ [ Int("AlicenewBoxbotI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
AlicenewBoxbotA = [ [ Int("AlicenewBoxbotA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
AlicenewBoxoutputC = [ True, True, True, True ]
AlicenewBoxoutputI = [ [ Int("AlicenewBoxoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
AlicenewBoxoutputA = [ [ Int("AlicenewBoxoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
Aliceexpandinput0C = [ Bool('Aliceexpandinput0C_%s' % i) for i in range(n) ]
Aliceexpandinput0I = [ [ Int("Aliceexpandinput0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
Aliceexpandinput0A = [ [ Int("Aliceexpandinput0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
Aliceexpandinput1C = [ Bool('Aliceexpandinput1C_%s' % i) for i in range(n) ]
Aliceexpandinput1I = [ [ Int("Aliceexpandinput1I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
Aliceexpandinput1A = [ [ Int("Aliceexpandinput1A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
AliceexpandoutputC = [ True, True, True, False ]
AliceexpandoutputI = [ [ Int("AliceexpandoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
AliceexpandoutputA = [ [ Int("AliceexpandoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
AliceIDbotC = [ Bool('AliceIDbotC_%s' % i) for i in range(n) ]
AliceIDbotI = [ [ Int("AliceIDbotI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
AliceIDbotA = [ [ Int("AliceIDbotA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
AliceIDoutputC = [ True, True, True, True ]
AliceIDoutputI = [ [ Int("AliceIDoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
AliceIDoutputA = [ [ Int("AliceIDoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
AliceaddCommentinput0C = [ Bool('AliceaddCommentinput0C_%s' % i) for i in range(n) ]
AliceaddCommentinput0I = [ [ Int("AliceaddCommentinput0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
AliceaddCommentinput0A = [ [ Int("AliceaddCommentinput0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
AliceaddCommentinput1C = [ Bool('AliceaddCommentinput1C_%s' % i) for i in range(n) ]
AliceaddCommentinput1I = [ [ Int("AliceaddCommentinput1I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
AliceaddCommentinput1A = [ [ Int("AliceaddCommentinput1A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
AliceaddCommentoutputC = [ True, False, False, False ]
AliceaddCommentoutputI = [ [ Int("AliceaddCommentoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
AliceaddCommentoutputA = [ [ Int("AliceaddCommentoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
Alicerange0 = [ And(0 <= Aliceqs[i][j], 0 <= Aliceqc[i][j], 0 <= AlicenewBoxoutputI[i][j], 0 <= AlicenewBoxoutputA[i][j], 0 <= AlicenewBoxbotI[i][j], 0 <= AlicenewBoxbotA[i][j], 0 <= AliceexpandoutputI[i][j], 0 <= AliceexpandoutputA[i][j], 0 <= Aliceexpandinput0I[i][j], 0 <= Aliceexpandinput0A[i][j], 0 <= Aliceexpandinput1I[i][j], 0 <= Aliceexpandinput1A[i][j], 0 <= AliceIDoutputI[i][j], 0 <= AliceIDoutputA[i][j], 0 <= AliceIDbotI[i][j], 0 <= AliceIDbotA[i][j], 0 <= AliceaddCommentoutputI[i][j], 0 <= AliceaddCommentoutputA[i][j], 0 <= AliceaddCommentinput0I[i][j], 0 <= AliceaddCommentinput0A[i][j], 0 <= AliceaddCommentinput1I[i][j], 0 <= AliceaddCommentinput1A[i][j]) for i in range(n) for j in range(n) ]
s.add(Alicerange0)
Alicerange1 = [And(sLe(Aliceqs[i], principals), sLe(Aliceqc[i], principals), sLe(AlicenewBoxoutputI[i], principals), sLe(AlicenewBoxoutputA[i], principals), sLe(AlicenewBoxbotI[i], principals), sLe(AlicenewBoxbotA[i], principals), sLe(AliceexpandoutputI[i], principals), sLe(AliceexpandoutputA[i], principals), sLe(Aliceexpandinput0I[i], principals), sLe(Aliceexpandinput0A[i], principals), sLe(Aliceexpandinput1I[i], principals), sLe(Aliceexpandinput1A[i], principals), sLe(AliceIDoutputI[i], principals), sLe(AliceIDoutputA[i], principals), sLe(AliceIDbotI[i], principals), sLe(AliceIDbotA[i], principals), sLe(AliceaddCommentoutputI[i], principals), sLe(AliceaddCommentoutputA[i], principals), sLe(AliceaddCommentinput0I[i], principals), sLe(AliceaddCommentinput0A[i], principals), sLe(AliceaddCommentinput1I[i], principals), sLe(AliceaddCommentinput1A[i], principals)) for i in range(n)]
s.add(Alicerange1)
Alicerange2 = [And(0 <= AliceOH[i]) for i in range(n)]
s.add(Alicerange2)
Alicerange3 = sLe(AliceOH, principals)
s.add(Alicerange3)
mapServqs = [ [ Int("mapServqs_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
mapServqc = [ [ Int("mapServqc_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
mapServOH = [ Int('mapServOH_%s' % i) for i in range(n) ] 
mapServgetMapinput0C = [ Bool('mapServgetMapinput0C_%s' % i) for i in range(n) ]
mapServgetMapinput0I = [ [ Int("mapServgetMapinput0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
mapServgetMapinput0A = [ [ Int("mapServgetMapinput0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
mapServgetMapoutputC = [ True, True, True, False ]
mapServgetMapoutputI = [ [ Int("mapServgetMapoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
mapServgetMapoutputA = [ [ Int("mapServgetMapoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
mapServrange0 = [ And(0 <= mapServqs[i][j], 0 <= mapServqc[i][j], 0 <= mapServgetMapoutputI[i][j], 0 <= mapServgetMapoutputA[i][j], 0 <= mapServgetMapinput0I[i][j], 0 <= mapServgetMapinput0A[i][j]) for i in range(n) for j in range(n) ]
s.add(mapServrange0)
mapServrange1 = [And(sLe(mapServqs[i], principals), sLe(mapServqc[i], principals), sLe(mapServgetMapoutputI[i], principals), sLe(mapServgetMapoutputA[i], principals), sLe(mapServgetMapinput0I[i], principals), sLe(mapServgetMapinput0A[i], principals)) for i in range(n)]
s.add(mapServrange1)
mapServrange2 = [And(0 <= mapServOH[i]) for i in range(n)]
s.add(mapServrange2)
mapServrange3 = sLe(mapServOH, principals)
s.add(mapServrange3)
#FieldT: Snapp
s.add(cLeH(SnappisFriendoutputC, SnappOH))
s.add(sIntegrity(SnappisFriendoutputI, Snappqs, SnappOH))
s.add(availabilityP(SnappisFriendoutputA, Snappqs, SnappOH))
s.add(cIntegrityE(SnappisFriendinput0I, Snappqc))
s.add(lableLe(SnappisFriendinput0C, SnappisFriendoutputC, SnappisFriendinput0I, SnappisFriendoutputI, SnappisFriendinput0A, SnappisFriendoutputA))
s.add(cIntegrityE(SnappisFriendinput1I, Snappqc))
s.add(lableLe(SnappisFriendinput1C, SnappisFriendoutputC, SnappisFriendinput1I, SnappisFriendoutputI, SnappisFriendinput1A, SnappisFriendoutputA))
#FieldT: Bob
s.add(cLeH(BobcommentoutputC, BobOH))
s.add(sIntegrity(BobcommentoutputI, Bobqs, BobOH))
s.add(availabilityP(BobcommentoutputA, Bobqs, BobOH))
s.add(cIntegrityE(BobcommentbotI, Bobqc))
s.add(lableLe(BobcommentbotC, BobcommentoutputC, BobcommentbotI, BobcommentoutputI, BobcommentbotA, BobcommentoutputA))
s.add(cLeH(BoblocationoutputC, BobOH))
s.add(sIntegrity(BoblocationoutputI, Bobqs, BobOH))
s.add(availabilityP(BoblocationoutputA, Bobqs, BobOH))
s.add(cIntegrityE(BoblocationbotI, Bobqc))
s.add(lableLe(BoblocationbotC, BoblocationoutputC, BoblocationbotI, BoblocationoutputI, BoblocationbotA, BoblocationoutputA))
s.add(cLeH(BobIDoutputC, BobOH))
s.add(sIntegrity(BobIDoutputI, Bobqs, BobOH))
s.add(availabilityP(BobIDoutputA, Bobqs, BobOH))
s.add(cIntegrityE(BobIDbotI, Bobqc))
s.add(lableLe(BobIDbotC, BobIDoutputC, BobIDbotI, BobIDoutputI, BobIDbotA, BobIDoutputA))
#FieldT: Alice
s.add(cLeH(AlicenewBoxoutputC, AliceOH))
s.add(sIntegrity(AlicenewBoxoutputI, Aliceqs, AliceOH))
s.add(availabilityP(AlicenewBoxoutputA, Aliceqs, AliceOH))
s.add(cIntegrityE(AlicenewBoxbotI, Aliceqc))
s.add(lableLe(AlicenewBoxbotC, AlicenewBoxoutputC, AlicenewBoxbotI, AlicenewBoxoutputI, AlicenewBoxbotA, AlicenewBoxoutputA))
s.add(cLeH(AliceexpandoutputC, AliceOH))
s.add(sIntegrity(AliceexpandoutputI, Aliceqs, AliceOH))
s.add(availabilityP(AliceexpandoutputA, Aliceqs, AliceOH))
s.add(cIntegrityE(Aliceexpandinput0I, Aliceqc))
s.add(lableLe(Aliceexpandinput0C, AliceexpandoutputC, Aliceexpandinput0I, AliceexpandoutputI, Aliceexpandinput0A, AliceexpandoutputA))
s.add(cIntegrityE(Aliceexpandinput1I, Aliceqc))
s.add(lableLe(Aliceexpandinput1C, AliceexpandoutputC, Aliceexpandinput1I, AliceexpandoutputI, Aliceexpandinput1A, AliceexpandoutputA))
s.add(cLeH(AliceIDoutputC, AliceOH))
s.add(sIntegrity(AliceIDoutputI, Aliceqs, AliceOH))
s.add(availabilityP(AliceIDoutputA, Aliceqs, AliceOH))
s.add(cIntegrityE(AliceIDbotI, Aliceqc))
s.add(lableLe(AliceIDbotC, AliceIDoutputC, AliceIDbotI, AliceIDoutputI, AliceIDbotA, AliceIDoutputA))
s.add(cLeH(AliceaddCommentoutputC, AliceOH))
s.add(sIntegrity(AliceaddCommentoutputI, Aliceqs, AliceOH))
s.add(availabilityP(AliceaddCommentoutputA, Aliceqs, AliceOH))
s.add(cIntegrityE(AliceaddCommentinput0I, Aliceqc))
s.add(lableLe(AliceaddCommentinput0C, AliceaddCommentoutputC, AliceaddCommentinput0I, AliceaddCommentoutputI, AliceaddCommentinput0A, AliceaddCommentoutputA))
s.add(cIntegrityE(AliceaddCommentinput1I, Aliceqc))
s.add(lableLe(AliceaddCommentinput1C, AliceaddCommentoutputC, AliceaddCommentinput1I, AliceaddCommentoutputI, AliceaddCommentinput1A, AliceaddCommentoutputA))
#FieldT: mapServ
s.add(cLeH(mapServgetMapoutputC, mapServOH))
s.add(sIntegrity(mapServgetMapoutputI, mapServqs, mapServOH))
s.add(availabilityP(mapServgetMapoutputA, mapServqs, mapServOH))
s.add(cIntegrityE(mapServgetMapinput0I, mapServqc))
s.add(lableLe(mapServgetMapinput0C, mapServgetMapoutputC, mapServgetMapinput0I, mapServgetMapoutputI, mapServgetMapinput0A, mapServgetMapoutputA))
#MethodT: m8
#ObjCallT: let b = Alice.newBox() in let aID = Alice.ID() in let bID = Bob.ID() in let x11 = Snapp.isFriend(aID, bID) in If (x11) then (let bLoc = Bob.location() in let newB = Alice.expand(b, bLoc) in let m = mapServ.getMap(newB) in let bc = Bob.comment() in let x5 = Alice.addComment(m, bc) in this.ret(x5)) else (this.ret(b))
s.add(cLeH(AlicenewBoxoutputC, m8H))
s.add(availabilityP(AlicenewBoxbotA, Aliceqc, m8H))
#ThisCallT: this.m7(b)
s.add(cLe(m8conxtC, m7conxtC))
s.add(bLe(m7conxtI, m8conxtI))
s.add(bLe(m7conxtA, m8conxtA))
s.add(cLe(AlicenewBoxoutputC, m7bC))
s.add(cLe(botC, m7bC))
s.add(bLe(m7bI, AlicenewBoxoutputI))
s.add(bLe(m7bI, botI))
s.add(bLe(m7bA, AlicenewBoxoutputA))
s.add(bLe(m7bA, botA))
s.add(cLe(m8conxtC, m7bC))
s.add(bLe(m7bI, m8conxtI))
s.add(bLe(m7bA, m8conxtA))
s.add(availabilityP(m7bA, m7Q, m8H))
s.add(cIntegrityE(m8botI, m8Q))
s.add(cLeH(m8botC, m8H))
s.add(cLeH(m8conxtC, m8H))
#MethodT: m7
#ObjCallT: let aID = Alice.ID() in let bID = Bob.ID() in let x11 = Snapp.isFriend(aID, bID) in If (x11) then (let bLoc = Bob.location() in let newB = Alice.expand(b, bLoc) in let m = mapServ.getMap(newB) in let bc = Bob.comment() in let x5 = Alice.addComment(m, bc) in this.ret(x5)) else (this.ret(b))
s.add(cLeH(AliceIDoutputC, m7H))
s.add(availabilityP(AliceIDbotA, Aliceqc, m7H))
#ThisCallT: this.m6(b, aID)
s.add(cLe(m7conxtC, m6conxtC))
s.add(bLe(m6conxtI, m7conxtI))
s.add(bLe(m6conxtA, m7conxtA))
s.add(cLe(m7bC, m6bC))
s.add(cLe(botC, m6bC))
s.add(bLe(m6bI, m7bI))
s.add(bLe(m6bI, botI))
s.add(bLe(m6bA, m7bA))
s.add(bLe(m6bA, botA))
s.add(cLe(m7conxtC, m6bC))
s.add(bLe(m6bI, m7conxtI))
s.add(bLe(m6bA, m7conxtA))
s.add(availabilityP(m6bA, m6Q, m7H))
s.add(cLe(AliceIDoutputC, m6aIDC))
s.add(cLe(botC, m6aIDC))
s.add(bLe(m6aIDI, AliceIDoutputI))
s.add(bLe(m6aIDI, botI))
s.add(bLe(m6aIDA, AliceIDoutputA))
s.add(bLe(m6aIDA, botA))
s.add(cLe(m7conxtC, m6aIDC))
s.add(bLe(m6aIDI, m7conxtI))
s.add(bLe(m6aIDA, m7conxtA))
s.add(availabilityP(m6aIDA, m6Q, m7H))
s.add(cIntegrityE(m7bI, m7Q))
s.add(cLeH(m7bC, m7H))
s.add(cLeH(m7conxtC, m7H))
#MethodT: m6
#ObjCallT: let bID = Bob.ID() in let x11 = Snapp.isFriend(aID, bID) in If (x11) then (let bLoc = Bob.location() in let newB = Alice.expand(b, bLoc) in let m = mapServ.getMap(newB) in let bc = Bob.comment() in let x5 = Alice.addComment(m, bc) in this.ret(x5)) else (this.ret(b))
s.add(cLeH(BobIDoutputC, m6H))
s.add(availabilityP(BobIDbotA, Bobqc, m6H))
#ThisCallT: this.m5(b, bID, aID)
s.add(cLe(m6conxtC, m5conxtC))
s.add(bLe(m5conxtI, m6conxtI))
s.add(bLe(m5conxtA, m6conxtA))
s.add(cLe(m6bC, m5bC))
s.add(cLe(botC, m5bC))
s.add(bLe(m5bI, m6bI))
s.add(bLe(m5bI, botI))
s.add(bLe(m5bA, m6bA))
s.add(bLe(m5bA, botA))
s.add(cLe(m6conxtC, m5bC))
s.add(bLe(m5bI, m6conxtI))
s.add(bLe(m5bA, m6conxtA))
s.add(availabilityP(m5bA, m5Q, m6H))
s.add(cLe(BobIDoutputC, m5bIDC))
s.add(cLe(botC, m5bIDC))
s.add(bLe(m5bIDI, BobIDoutputI))
s.add(bLe(m5bIDI, botI))
s.add(bLe(m5bIDA, BobIDoutputA))
s.add(bLe(m5bIDA, botA))
s.add(cLe(m6conxtC, m5bIDC))
s.add(bLe(m5bIDI, m6conxtI))
s.add(bLe(m5bIDA, m6conxtA))
s.add(availabilityP(m5bIDA, m5Q, m6H))
s.add(cLe(m6aIDC, m5aIDC))
s.add(cLe(botC, m5aIDC))
s.add(bLe(m5aIDI, m6aIDI))
s.add(bLe(m5aIDI, botI))
s.add(bLe(m5aIDA, m6aIDA))
s.add(bLe(m5aIDA, botA))
s.add(cLe(m6conxtC, m5aIDC))
s.add(bLe(m5aIDI, m6conxtI))
s.add(bLe(m5aIDA, m6conxtA))
s.add(availabilityP(m5aIDA, m5Q, m6H))
s.add(cIntegrityE(m6bI, m6Q))
s.add(cIntegrityE(m6aIDI, m6Q))
s.add(cLeH(m6bC, m6H))
s.add(cLeH(m6aIDC, m6H))
s.add(cLeH(m6conxtC, m6H))
#MethodT: m5
#ObjCallT: let x11 = Snapp.isFriend(aID, bID) in If (x11) then (let bLoc = Bob.location() in let newB = Alice.expand(b, bLoc) in let m = mapServ.getMap(newB) in let bc = Bob.comment() in let x5 = Alice.addComment(m, bc) in this.ret(x5)) else (this.ret(b))
s.add(cLeH(SnappisFriendoutputC, m5H))
s.add(cLe(m5aIDC, SnappisFriendinput0C))
s.add(cLe(botC, SnappisFriendinput0C))
s.add(bLe(SnappisFriendinput0I, m5aIDI))
s.add(bLe(SnappisFriendinput0I, botI))
s.add(bLe(SnappisFriendinput0A, m5aIDA))
s.add(bLe(SnappisFriendinput0A, botA))
s.add(availabilityP(SnappisFriendinput0A, Snappqc, m5H))
s.add(cLe(m5bIDC, SnappisFriendinput1C))
s.add(cLe(botC, SnappisFriendinput1C))
s.add(bLe(SnappisFriendinput1I, m5bIDI))
s.add(bLe(SnappisFriendinput1I, botI))
s.add(bLe(SnappisFriendinput1A, m5bIDA))
s.add(bLe(SnappisFriendinput1A, botA))
s.add(availabilityP(SnappisFriendinput1A, Snappqc, m5H))
#IfT: If (x11) then (this.m4(b)) else (this.ret(b))
#ThisCallT: this.m4(b)
s.add(cLe(m5conxtC, m4conxtC))
s.add(cLe(SnappisFriendoutputC, m4conxtC))
s.add(cLe(botC, m4conxtC))
s.add(cLe(SnappisFriendoutputC, m4conxtC))
s.add(cLe(botC, m4conxtC))
s.add(bLe(m4conxtI, m5conxtI))
s.add(bLe(m4conxtI, SnappisFriendoutputI))
s.add(bLe(m4conxtI, botI))
s.add(bLe(m4conxtI, SnappisFriendoutputI))
s.add(bLe(m4conxtI, botI))
s.add(bLe(m4conxtA, m5conxtA))
s.add(bLe(m4conxtA, SnappisFriendoutputA))
s.add(bLe(m4conxtA, botA))
s.add(bLe(m4conxtA, SnappisFriendoutputA))
s.add(bLe(m4conxtA, botA))
s.add(cLe(m5bC, m4bC))
s.add(cLe(botC, m4bC))
s.add(bLe(m4bI, m5bI))
s.add(bLe(m4bI, botI))
s.add(bLe(m4bA, m5bA))
s.add(bLe(m4bA, botA))
s.add(cLe(m5conxtC, m4bC))
s.add(cLe(SnappisFriendoutputC, m4bC))
s.add(cLe(botC, m4bC))
s.add(cLe(SnappisFriendoutputC, m4bC))
s.add(cLe(botC, m4bC))
s.add(bLe(m4bI, m5conxtI))
s.add(bLe(m4bI, SnappisFriendoutputI))
s.add(bLe(m4bI, botI))
s.add(bLe(m4bI, SnappisFriendoutputI))
s.add(bLe(m4bI, botI))
s.add(bLe(m4bA, m5conxtA))
s.add(bLe(m4bA, SnappisFriendoutputA))
s.add(bLe(m4bA, botA))
s.add(bLe(m4bA, SnappisFriendoutputA))
s.add(bLe(m4bA, botA))
s.add(availabilityP(m4bA, m4Q, m5H))
#ThisCallT: this.ret(b)
s.add(cLe(m5conxtC, resultC))
s.add(cLe(SnappisFriendoutputC, resultC))
s.add(cLe(botC, resultC))
s.add(cLe(SnappisFriendoutputC, resultC))
s.add(cLe(botC, resultC))
s.add(bLe(resultI, m5conxtI))
s.add(bLe(resultI, SnappisFriendoutputI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, SnappisFriendoutputI))
s.add(bLe(resultI, botI))
s.add(bLe(resultA, m5conxtA))
s.add(bLe(resultA, SnappisFriendoutputA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, SnappisFriendoutputA))
s.add(bLe(resultA, botA))
s.add(cLe(m5bC, resultC))
s.add(cLe(botC, resultC))
s.add(cLe(botC, resultC))
s.add(bLe(resultI, m5bI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, botI))
s.add(bLe(resultA, m5bA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, botA))
s.add(cLe(m5conxtC, resultC))
s.add(cLe(SnappisFriendoutputC, resultC))
s.add(cLe(botC, resultC))
s.add(cLe(SnappisFriendoutputC, resultC))
s.add(cLe(botC, resultC))
s.add(bLe(resultI, m5conxtI))
s.add(bLe(resultI, SnappisFriendoutputI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, SnappisFriendoutputI))
s.add(bLe(resultI, botI))
s.add(bLe(resultA, m5conxtA))
s.add(bLe(resultA, SnappisFriendoutputA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, SnappisFriendoutputA))
s.add(bLe(resultA, botA))
s.add(availabilityP(resultA, resQ, m5H))
s.add(cIntegrityE(m5bI, m5Q))
s.add(cIntegrityE(m5bIDI, m5Q))
s.add(cIntegrityE(m5aIDI, m5Q))
s.add(cLeH(m5bC, m5H))
s.add(cLeH(m5bIDC, m5H))
s.add(cLeH(m5aIDC, m5H))
s.add(cLeH(m5conxtC, m5H))
#MethodT: m4
#ObjCallT: let bLoc = Bob.location() in let newB = Alice.expand(b, bLoc) in let m = mapServ.getMap(newB) in let bc = Bob.comment() in let x5 = Alice.addComment(m, bc) in this.ret(x5)
s.add(cLeH(BoblocationoutputC, m4H))
s.add(availabilityP(BoblocationbotA, Bobqc, m4H))
#ThisCallT: this.m3(b, bLoc)
s.add(cLe(m4conxtC, m3conxtC))
s.add(bLe(m3conxtI, m4conxtI))
s.add(bLe(m3conxtA, m4conxtA))
s.add(cLe(m4bC, m3bC))
s.add(cLe(botC, m3bC))
s.add(bLe(m3bI, m4bI))
s.add(bLe(m3bI, botI))
s.add(bLe(m3bA, m4bA))
s.add(bLe(m3bA, botA))
s.add(cLe(m4conxtC, m3bC))
s.add(bLe(m3bI, m4conxtI))
s.add(bLe(m3bA, m4conxtA))
s.add(availabilityP(m3bA, m3Q, m4H))
s.add(cLe(BoblocationoutputC, m3bLocC))
s.add(cLe(botC, m3bLocC))
s.add(bLe(m3bLocI, BoblocationoutputI))
s.add(bLe(m3bLocI, botI))
s.add(bLe(m3bLocA, BoblocationoutputA))
s.add(bLe(m3bLocA, botA))
s.add(cLe(m4conxtC, m3bLocC))
s.add(bLe(m3bLocI, m4conxtI))
s.add(bLe(m3bLocA, m4conxtA))
s.add(availabilityP(m3bLocA, m3Q, m4H))
s.add(cIntegrityE(m4bI, m4Q))
s.add(cLeH(m4bC, m4H))
s.add(cLeH(m4conxtC, m4H))
#MethodT: m3
#ObjCallT: let newB = Alice.expand(b, bLoc) in let m = mapServ.getMap(newB) in let bc = Bob.comment() in let x5 = Alice.addComment(m, bc) in this.ret(x5)
s.add(cLeH(AliceexpandoutputC, m3H))
s.add(cLe(m3bC, Aliceexpandinput0C))
s.add(cLe(botC, Aliceexpandinput0C))
s.add(bLe(Aliceexpandinput0I, m3bI))
s.add(bLe(Aliceexpandinput0I, botI))
s.add(bLe(Aliceexpandinput0A, m3bA))
s.add(bLe(Aliceexpandinput0A, botA))
s.add(availabilityP(Aliceexpandinput0A, Aliceqc, m3H))
s.add(cLe(m3bLocC, Aliceexpandinput1C))
s.add(cLe(botC, Aliceexpandinput1C))
s.add(bLe(Aliceexpandinput1I, m3bLocI))
s.add(bLe(Aliceexpandinput1I, botI))
s.add(bLe(Aliceexpandinput1A, m3bLocA))
s.add(bLe(Aliceexpandinput1A, botA))
s.add(availabilityP(Aliceexpandinput1A, Aliceqc, m3H))
#ThisCallT: this.m2(newB)
s.add(cLe(m3conxtC, m2conxtC))
s.add(bLe(m2conxtI, m3conxtI))
s.add(bLe(m2conxtA, m3conxtA))
s.add(cLe(AliceexpandoutputC, m2newBC))
s.add(cLe(botC, m2newBC))
s.add(bLe(m2newBI, AliceexpandoutputI))
s.add(bLe(m2newBI, botI))
s.add(bLe(m2newBA, AliceexpandoutputA))
s.add(bLe(m2newBA, botA))
s.add(cLe(m3conxtC, m2newBC))
s.add(bLe(m2newBI, m3conxtI))
s.add(bLe(m2newBA, m3conxtA))
s.add(availabilityP(m2newBA, m2Q, m3H))
s.add(cIntegrityE(m3bI, m3Q))
s.add(cIntegrityE(m3bLocI, m3Q))
s.add(cLeH(m3bC, m3H))
s.add(cLeH(m3bLocC, m3H))
s.add(cLeH(m3conxtC, m3H))
#MethodT: m2
#ObjCallT: let m = mapServ.getMap(newB) in let bc = Bob.comment() in let x5 = Alice.addComment(m, bc) in this.ret(x5)
s.add(cLeH(mapServgetMapoutputC, m2H))
s.add(cLe(m2newBC, mapServgetMapinput0C))
s.add(cLe(botC, mapServgetMapinput0C))
s.add(bLe(mapServgetMapinput0I, m2newBI))
s.add(bLe(mapServgetMapinput0I, botI))
s.add(bLe(mapServgetMapinput0A, m2newBA))
s.add(bLe(mapServgetMapinput0A, botA))
s.add(availabilityP(mapServgetMapinput0A, mapServqc, m2H))
#ThisCallT: this.m1(m)
s.add(cLe(m2conxtC, m1conxtC))
s.add(bLe(m1conxtI, m2conxtI))
s.add(bLe(m1conxtA, m2conxtA))
s.add(cLe(mapServgetMapoutputC, m1mC))
s.add(cLe(botC, m1mC))
s.add(bLe(m1mI, mapServgetMapoutputI))
s.add(bLe(m1mI, botI))
s.add(bLe(m1mA, mapServgetMapoutputA))
s.add(bLe(m1mA, botA))
s.add(cLe(m2conxtC, m1mC))
s.add(bLe(m1mI, m2conxtI))
s.add(bLe(m1mA, m2conxtA))
s.add(availabilityP(m1mA, m1Q, m2H))
s.add(cIntegrityE(m2newBI, m2Q))
s.add(cLeH(m2newBC, m2H))
s.add(cLeH(m2conxtC, m2H))
#MethodT: m1
#ObjCallT: let bc = Bob.comment() in let x5 = Alice.addComment(m, bc) in this.ret(x5)
s.add(cLeH(BobcommentoutputC, m1H))
s.add(availabilityP(BobcommentbotA, Bobqc, m1H))
#ThisCallT: this.m0(bc, m)
s.add(cLe(m1conxtC, m0conxtC))
s.add(bLe(m0conxtI, m1conxtI))
s.add(bLe(m0conxtA, m1conxtA))
s.add(cLe(BobcommentoutputC, m0bcC))
s.add(cLe(botC, m0bcC))
s.add(bLe(m0bcI, BobcommentoutputI))
s.add(bLe(m0bcI, botI))
s.add(bLe(m0bcA, BobcommentoutputA))
s.add(bLe(m0bcA, botA))
s.add(cLe(m1conxtC, m0bcC))
s.add(bLe(m0bcI, m1conxtI))
s.add(bLe(m0bcA, m1conxtA))
s.add(availabilityP(m0bcA, m0Q, m1H))
s.add(cLe(m1mC, m0mC))
s.add(cLe(botC, m0mC))
s.add(bLe(m0mI, m1mI))
s.add(bLe(m0mI, botI))
s.add(bLe(m0mA, m1mA))
s.add(bLe(m0mA, botA))
s.add(cLe(m1conxtC, m0mC))
s.add(bLe(m0mI, m1conxtI))
s.add(bLe(m0mA, m1conxtA))
s.add(availabilityP(m0mA, m0Q, m1H))
s.add(cIntegrityE(m1mI, m1Q))
s.add(cLeH(m1mC, m1H))
s.add(cLeH(m1conxtC, m1H))
#MethodT: m0
#ObjCallT: let x5 = Alice.addComment(m, bc) in this.ret(x5)
s.add(cLeH(AliceaddCommentoutputC, m0H))
s.add(cLe(m0mC, AliceaddCommentinput0C))
s.add(cLe(botC, AliceaddCommentinput0C))
s.add(bLe(AliceaddCommentinput0I, m0mI))
s.add(bLe(AliceaddCommentinput0I, botI))
s.add(bLe(AliceaddCommentinput0A, m0mA))
s.add(bLe(AliceaddCommentinput0A, botA))
s.add(availabilityP(AliceaddCommentinput0A, Aliceqc, m0H))
s.add(cLe(m0bcC, AliceaddCommentinput1C))
s.add(cLe(botC, AliceaddCommentinput1C))
s.add(bLe(AliceaddCommentinput1I, m0bcI))
s.add(bLe(AliceaddCommentinput1I, botI))
s.add(bLe(AliceaddCommentinput1A, m0bcA))
s.add(bLe(AliceaddCommentinput1A, botA))
s.add(availabilityP(AliceaddCommentinput1A, Aliceqc, m0H))
#ThisCallT: this.ret(x5)
s.add(cLe(m0conxtC, resultC))
s.add(bLe(resultI, m0conxtI))
s.add(bLe(resultA, m0conxtA))
s.add(cLe(AliceaddCommentoutputC, resultC))
s.add(cLe(botC, resultC))
s.add(bLe(resultI, AliceaddCommentoutputI))
s.add(bLe(resultI, botI))
s.add(bLe(resultA, AliceaddCommentoutputA))
s.add(bLe(resultA, botA))
s.add(cLe(m0conxtC, resultC))
s.add(bLe(resultI, m0conxtI))
s.add(bLe(resultA, m0conxtA))
s.add(availabilityP(resultA, resQ, m0H))
s.add(cIntegrityE(m0bcI, m0Q))
s.add(cIntegrityE(m0mI, m0Q))
s.add(cLeH(m0bcC, m0H))
s.add(cLeH(m0mC, m0H))
s.add(cLeH(m0conxtC, m0H))
#MethodT: ret
s.add(cLeH(resultC, resH))
s.add(cIntegrityE(resultI, resQ))
s.add(cLe(startC, m8conxtC))
s.add(bLe(m8conxtI, startI))
s.add(bLe(m8conxtA, startA))
s.add(availabilityP(m8botA, m8Q, resH))
s.add(cLe(startC, m8botC))
s.add(bLe(m8botI, startI))
s.add(bLe(m8botA, startA))
print("n = 4")
print("principals = [4, 4, 7, 4]")
weight = [2, 2, 1, 1]

s.minimize(sum(m0H[i] * weight[i] for i in range(n)) + sum(m1H[i] * weight[i] for i in range(n)) + sum(m2H[i] * weight[i] for i in range(n)) + sum(m3H[i] * weight[i] for i in range(n)) + sum(m4H[i] * weight[i] for i in range(n)) + sum(m5H[i] * weight[i] for i in range(n)) + sum(m6H[i] * weight[i] for i in range(n)) + sum(m7H[i] * weight[i] for i in range(n)) + sum(m8H[i] * weight[i] for i in range(n)) + sum(SnappOH[i] * weight[i] for i in range(n)) + sum(BobOH[i] * weight[i] for i in range(n)) + sum(AliceOH[i] * weight[i] for i in range(n)) + sum(mapServOH[i] * weight[i] for i in range(n)) + sum(Snappqs[0][i] * weight[i] for i in range(n)) + sum(Snappqs[1][i] * weight[i] for i in range(n)) + sum(Snappqs[2][i] * weight[i] for i in range(n)) + sum(Snappqs[3][i] * weight[i] for i in range(n)) + sum(Bobqs[0][i] * weight[i] for i in range(n)) + sum(Bobqs[1][i] * weight[i] for i in range(n)) + sum(Bobqs[2][i] * weight[i] for i in range(n)) + sum(Bobqs[3][i] * weight[i] for i in range(n)) + sum(Aliceqs[0][i] * weight[i] for i in range(n)) + sum(Aliceqs[1][i] * weight[i] for i in range(n)) + sum(Aliceqs[2][i] * weight[i] for i in range(n)) + sum(Aliceqs[3][i] * weight[i] for i in range(n)) + sum(mapServqs[0][i] * weight[i] for i in range(n)) + sum(mapServqs[1][i] * weight[i] for i in range(n)) + sum(mapServqs[2][i] * weight[i] for i in range(n)) + sum(mapServqs[3][i] * weight[i] for i in range(n)) + sum(Snappqc[0][i] * weight[i] for i in range(n)) + sum(Snappqc[1][i] * weight[i] for i in range(n)) + sum(Snappqc[2][i] * weight[i] for i in range(n)) + sum(Snappqc[3][i] * weight[i] for i in range(n)) + sum(Bobqc[0][i] * weight[i] for i in range(n)) + sum(Bobqc[1][i] * weight[i] for i in range(n)) + sum(Bobqc[2][i] * weight[i] for i in range(n)) + sum(Bobqc[3][i] * weight[i] for i in range(n)) + sum(Aliceqc[0][i] * weight[i] for i in range(n)) + sum(Aliceqc[1][i] * weight[i] for i in range(n)) + sum(Aliceqc[2][i] * weight[i] for i in range(n)) + sum(Aliceqc[3][i] * weight[i] for i in range(n)) + sum(mapServqc[0][i] * weight[i] for i in range(n)) + sum(mapServqc[1][i] * weight[i] for i in range(n)) + sum(mapServqc[2][i] * weight[i] for i in range(n)) + sum(mapServqc[3][i] * weight[i] for i in range(n)) + sum(resQ[0]) + sum(resQ[1]) + sum(resQ[2]) + sum(resQ[3]) + sum(m0Q[0]) + sum(m0Q[1]) + sum(m0Q[2]) + sum(m0Q[3]) + sum(m1Q[0]) + sum(m1Q[1]) + sum(m1Q[2]) + sum(m1Q[3]) + sum(m2Q[0]) + sum(m2Q[1]) + sum(m2Q[2]) + sum(m2Q[3]) + sum(m3Q[0]) + sum(m3Q[1]) + sum(m3Q[2]) + sum(m3Q[3]) + sum(m4Q[0]) + sum(m4Q[1]) + sum(m4Q[2]) + sum(m4Q[3]) + sum(m5Q[0]) + sum(m5Q[1]) + sum(m5Q[2]) + sum(m5Q[3]) + sum(m6Q[0]) + sum(m6Q[1]) + sum(m6Q[2]) + sum(m6Q[3]) + sum(m7Q[0]) + sum(m7Q[1]) + sum(m7Q[2]) + sum(m7Q[3]) + sum(m8Q[0]) + sum(m8Q[1]) + sum(m8Q[2]) + sum(m8Q[3]))
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
print("Snappqs:", [m[e].as_long() for qs in Snappqs for e in qs])
print("Bobqs:", [m[e].as_long() for qs in Bobqs for e in qs])
print("Aliceqs:", [m[e].as_long() for qs in Aliceqs for e in qs])
print("mapServqs:", [m[e].as_long() for qs in mapServqs for e in qs])
print("Snappqc:", [m[e].as_long() for qs in Snappqc for e in qs])
print("Bobqc:", [m[e].as_long() for qs in Bobqc for e in qs])
print("Aliceqc:", [m[e].as_long() for qs in Aliceqc for e in qs])
print("mapServqc:", [m[e].as_long() for qs in mapServqc for e in qs])
print("SnappOH:", [m[hInfo].as_long() for hInfo in SnappOH])
print("BobOH:", [m[hInfo].as_long() for hInfo in BobOH])
print("AliceOH:", [m[hInfo].as_long() for hInfo in AliceOH])
print("mapServOH:", [m[hInfo].as_long() for hInfo in mapServOH])
endT = time.time() - startT
print(endT)
