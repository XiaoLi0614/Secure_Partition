#include <Timer.h>


#include "Timer.inc"

void Timer::_instance_init() {
    _I_("Doing initialisation for class: Timer");
    
}


//#line 14 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"

//#line 15 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"

//#line 16 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"

//#line 19 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
void Timer::_constructor(x10_int n) {
    this->x10::lang::Ref::_constructor();
    
    //#line 20 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    ((x10aux::ref<Timer>)this)->FMGL(startTimes) = x10::lang::Rail<x10_double >::make(n, x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_double> > >(x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_double> >(x10aux::ref<Timer__closure__0>(new (x10aux::alloc<x10::lang::Fun_0_1<x10_int, x10_double> >(sizeof(Timer__closure__0)))Timer__closure__0()))));
    
    //#line 21 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    ((x10aux::ref<Timer>)this)->FMGL(elapsedTimes) = x10::lang::Rail<x10_double >::make(n, x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_double> > >(x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_double> >(x10aux::ref<Timer__closure__1>(new (x10aux::alloc<x10::lang::Fun_0_1<x10_int, x10_double> >(sizeof(Timer__closure__1)))Timer__closure__1()))));
    
    //#line 22 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    ((x10aux::ref<Timer>)this)->FMGL(totalTimes) = x10::lang::Rail<x10_double >::make(n, x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_double> > >(x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_double> >(x10aux::ref<Timer__closure__2>(new (x10aux::alloc<x10::lang::Fun_0_1<x10_int, x10_double> >(sizeof(Timer__closure__2)))Timer__closure__2()))));
    
}x10aux::ref<Timer> Timer::_make(x10_int n) {
    x10aux::ref<Timer> this_ = new (x10aux::alloc<Timer>()) Timer();
    this_->_constructor(n);
    return this_;
}



//#line 26 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
void Timer::_constructor() {
    this->_constructor(((x10_int)0));
    
}x10aux::ref<Timer> Timer::_make() {
    x10aux::ref<Timer> this_ = new (x10aux::alloc<Timer>()) Timer();
    this_->_constructor();
    return this_;
}



//#line 30 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
void Timer::start(x10_int n) {
    
    //#line 31 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    (*Transfer::transfer<x10aux::ref<x10::lang::Rail<x10_double > > >(((x10aux::ref<Timer>)this)->
                                                                        FMGL(startTimes)))[n] = ((x10_double) (x10::lang::System::currentTimeMillis()));
}

//#line 34 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
void Timer::start() {
    
    //#line 35 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10aux::nullCheck(((x10aux::ref<Timer>)this))->start(((x10_int)0));
}

//#line 38 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
void Timer::stop(x10_int n) {
    
    //#line 39 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    (*Transfer::transfer<x10aux::ref<x10::lang::Rail<x10_double > > >(
        ((x10aux::ref<Timer>)this)->
          FMGL(elapsedTimes)))[n] = ((((x10_double) (x10::lang::System::currentTimeMillis()))) - ((*Transfer::transfer<x10aux::ref<x10::lang::Rail<x10_double > > >(
                                                                                                      ((x10aux::ref<Timer>)this)->
                                                                                                        FMGL(startTimes)))[n]));
    
    //#line 41 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    (__extension__ ({
        x10aux::ref<x10::lang::Rail<x10_double > > x =
          Transfer::transfer<x10aux::ref<x10::lang::Rail<x10_double > > >(
            ((x10aux::ref<Timer>)this)->
              FMGL(elapsedTimes));
        x10_int y0 =
          n;
        x10_double z =
          ((x10_double) (((x10_int)1000)));
        (*x)[y0] = (((*x)[y0]) / (z));
    }))
    ;
    
    //#line 42 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    (__extension__ ({
        x10aux::ref<x10::lang::Rail<x10_double > > x =
          Transfer::transfer<x10aux::ref<x10::lang::Rail<x10_double > > >(
            ((x10aux::ref<Timer>)this)->
              FMGL(totalTimes));
        x10_int y0 =
          n;
        x10_double z =
          (*Transfer::transfer<x10aux::ref<x10::lang::Rail<x10_double > > >(
              ((x10aux::ref<Timer>)this)->
                FMGL(elapsedTimes)))[n];
        (*x)[y0] = (((*x)[y0]) + (z));
    }))
    ;
}

//#line 45 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
void Timer::stop() {
    
    //#line 46 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10aux::nullCheck(((x10aux::ref<Timer>)this))->stop(
      ((x10_int)0));
}

//#line 49 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
x10_double Timer::readTimer(x10_int n) {
    
    //#line 50 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    return (*Transfer::transfer<x10aux::ref<x10::lang::Rail<x10_double > > >(
               ((x10aux::ref<Timer>)this)->
                 FMGL(totalTimes)))[n];
    
}

//#line 53 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
x10_double Timer::readTimer() {
    
    //#line 54 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    return x10aux::nullCheck(((x10aux::ref<Timer>)this))->readTimer(
             ((x10_int)0));
    
}

//#line 57 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
void Timer::resetTimer(x10_int n) {
    
    //#line 58 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    (*Transfer::transfer<x10aux::ref<x10::lang::Rail<x10_double > > >(
        ((x10aux::ref<Timer>)this)->
          FMGL(totalTimes)))[n] = ((x10_double) (((x10_int)0)));
    
    //#line 59 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    (*Transfer::transfer<x10aux::ref<x10::lang::Rail<x10_double > > >(
        ((x10aux::ref<Timer>)this)->
          FMGL(startTimes)))[n] = ((x10_double) (((x10_int)0)));
    
    //#line 60 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    (*Transfer::transfer<x10aux::ref<x10::lang::Rail<x10_double > > >(
        ((x10aux::ref<Timer>)this)->
          FMGL(elapsedTimes)))[n] = ((x10_double) (((x10_int)0)));
}

//#line 63 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
void Timer::resetTimer() {
    
    //#line 64 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10aux::nullCheck(((x10aux::ref<Timer>)this))->resetTimer(
      ((x10_int)0));
}

//#line 67 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
void Timer::resetAllTimers() {
    
    //#line 68 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10_int i = ((x10_int)0);
    
    //#line 69 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    while (((i) < (x10aux::nullCheck(((x10aux::ref<Timer>)this)->
                                       FMGL(startTimes))->
                     FMGL(length)))) {
        
        //#line 70 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        x10aux::nullCheck(((x10aux::ref<Timer>)this))->resetTimer(
          i);
        
        //#line 71 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        i += ((x10_int)1);
    }
    
}
const x10aux::serialization_id_t Timer::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(Timer::_deserializer<x10::lang::Ref>);

void Timer::_serialize_body(x10aux::serialization_buffer& buf, x10aux::addr_map& m) {
    x10::lang::Ref::_serialize_body(buf, m);
    
}

void Timer::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Ref::_deserialize_body(buf);
    
}

x10aux::RuntimeType Timer::rtt;
void Timer::_initRTT() {
    rtt.canonical = &rtt;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Ref>()};
    rtt.init(&rtt, "Timer", 1, parents, 0, NULL, NULL);
}

extern "C" { const char* LNMAP__Timer_cc = "N{\"Timer.cc\"} F{0:\"/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translatedbenchs/hand/mapreduce/generic/MapReduceLauncher.x10\",1:\"Timer\",2:\"this\",3:\"\",4:\"x10.lang.Int\",5:\"_constructor\",6:\"void\",7:\"x10_int\",8:\"start\",9:\"x10.lang.Void\",10:\"stop\",11:\"readTimer\",12:\"x10.lang.Double\",13:\"x10_double\",14:\"resetTimer\",15:\"resetAllTimers\",} L{70->0:39,138->0:58,64->0:36,143->0:59,67->0:38,129->0:54,77->0:41,132->0:55,13->0:14,135->0:57,15->0:15,17->0:16,154->0:63,19->0:19,157->0:64,23->0:20,159->0:65,26->0:21,148->0:60,29->0:22,91->0:42,151->0:61,168->0:69,173->0:70,162->0:67,111->0:46,40->0:26,108->0:45,165->0:68,105->0:43,119->0:50,116->0:49,55->0:31,113->0:47,52->0:30,178->0:72,126->0:53,57->0:32,177->0:71,63->0:35,123->0:51,180->0:73,60->0:34,} M{6 1.15()->9 1.15();13 1.11()->12 1.11();6 1.5()->3 1.2();6 1.14()->9 1.14();6 1.8()->9 1.8();6 1.10(7)->9 1.10(4);13 1.11(7)->12 1.11(4);6 1.8(7)->9 1.8(4);6 1.5(7)->3 1.2(4);6 1.14(7)->9 1.14(4);6 1.10()->9 1.10();}"; }
