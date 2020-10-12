#include <MapReducer.h>

x10aux::RuntimeType MapReducer<void, void, void, void>::rtt;
x10_int MapReducer<void, void, void, void>::FMGL(REDUCER_COUNT);
void MapReducer<void, void, void, void>::FMGL(REDUCER_COUNT__do_init)() {
    FMGL(REDUCER_COUNT__status) = x10aux::INITIALIZING;
    _I_("Doing static initialisation for field: MapReducer<void, void, void, void>.REDUCER_COUNT");
    x10_int __var24__ = x10::lang::Place_methods::FMGL(MAX_PLACES__get)();
    FMGL(REDUCER_COUNT) = __var24__;
    FMGL(REDUCER_COUNT__status) = x10aux::INITIALIZED;
}
void MapReducer<void, void, void, void>::FMGL(REDUCER_COUNT__init)() {
    if (x10aux::here == 0) {
        x10aux::status __var25__ = (x10aux::status)x10aux::atomic_ops::compareAndSet_32((volatile x10_int*)&FMGL(REDUCER_COUNT__status), (x10_int)x10aux::UNINITIALIZED, (x10_int)x10aux::INITIALIZING);
        if (__var25__ != x10aux::UNINITIALIZED) goto WAIT;
        FMGL(REDUCER_COUNT__do_init)();
        x10aux::StaticInitBroadcastDispatcher::broadcastStaticField(FMGL(REDUCER_COUNT),
                                                                    FMGL(REDUCER_COUNT__id));
        // Notify all waiting threads
        x10aux::StaticInitBroadcastDispatcher::notify();
    }
    WAIT:
    while (FMGL(REDUCER_COUNT__status) != x10aux::INITIALIZED) x10aux::StaticInitBroadcastDispatcher::await();
}
static void* __init__26 X10_PRAGMA_UNUSED = x10aux::InitDispatcher::addInitializer(MapReducer<void, void, void, void>::FMGL(REDUCER_COUNT__init));

volatile x10aux::status MapReducer<void, void, void, void>::FMGL(REDUCER_COUNT__status);
// extract value from a buffer
x10aux::ref<x10::lang::Ref> MapReducer<void, void, void, void>::FMGL(REDUCER_COUNT__deserialize)(x10aux::deserialization_buffer &buf) {
    FMGL(REDUCER_COUNT) = buf.read<x10_int>();
    MapReducer<void, void, void, void>::FMGL(REDUCER_COUNT__status) = x10aux::INITIALIZED;
    // Notify all waiting threads
    x10aux::StaticInitBroadcastDispatcher::notify();
    return x10aux::null;
}
const x10aux::serialization_id_t MapReducer<void, void, void, void>::FMGL(REDUCER_COUNT__id) =
  x10aux::StaticInitBroadcastDispatcher::addRoutine(MapReducer<void, void, void, void>::FMGL(REDUCER_COUNT__deserialize));


extern "C" { const char* LNMAP__MapReducer_h = "N{\"MapReducer.h\"} F{0:\"/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translatedbenchs/hand/mapreduce/generic/MapReduceLauncher.x10\",1:\"MapReducer\",2:\"this\",3:\"\",4:\"x10.util.Set[T1]\",5:\"MapReducer<FMGL(T1), FMGL(KI), FMGL(TI), FMGL(T2)>\",6:\"_constructor\",7:\"void\",8:\"x10aux::ref<x10::util::Set<FMGL(T1)> >\",9:\"compute\",10:\"x10.lang.Void\",11:\"phases\",12:\"distributePhase\",13:\"mapPhase\",14:\"redistributePhase\",15:\"reducePhase\",} L{136->0:145,141->0:145,131->0:145,15->0:164,134->0:145,17->0:151,152->0:145,19->0:152,257->0:182,18->0:139,262->0:184,21->0:139,156->0:145,157->0:145,23->0:109,158->0:145,24->0:166,26->0:154,29->0:110,148->0:145,149->0:145,33->0:166,168->0:146,38->0:166,175->0:161,36->0:166,37->0:113,172->0:158,43->0:166,47->0:116,51->0:166,185->0:162,55->0:166,190->0:163,53->0:117,52->0:166,59->0:166,58->0:119,57->0:118,61->0:166,60->0:166,181->0:162,71->0:169,206->0:163,67->0:122,76->0:170,197->0:163,77->0:125,79->0:170,72->0:123,73->0:123,221->0:162,87->0:171,86->0:128,216->0:175,80->0:127,83->0:170,92->0:172,214->0:175,95->0:131,215->0:175,89->0:129,210->0:175,238->0:175,237->0:162,101->0:174,99->0:134,96->0:132,97->0:173,111->0:136,229->0:175,227->0:162,104->0:175,105->0:135,254->0:180,119->0:137,249->0:178,246->0:176,244->0:162,125->0:145,245->0:175,} M{7 5.9(8)->10 1.9(4);7 5.11()->10 1.11();7 5.6()->3 1.2();7 5.13()->10 1.13();7 5.14()->10 1.14();7 5.9()->10 1.9();7 5.6(8)->3 1.2(4);7 5.15()->10 1.15();7 5.12()->10 1.12();}"; }
