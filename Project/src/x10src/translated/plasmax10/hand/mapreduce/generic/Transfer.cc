#include <Transfer.h>


#include "Transfer.inc"

void Transfer::_instance_init() {
    _I_("Doing initialisation for class: Transfer");
    
}


//#line 5 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"

//#line 4 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
void Transfer::_constructor() {
    this->x10::lang::Ref::_constructor();
    
}x10aux::ref<Transfer> Transfer::_make() {
    x10aux::ref<Transfer> this_ = new (x10aux::alloc<Transfer>()) Transfer();
    this_->_constructor();
    return this_;
}


const x10aux::serialization_id_t Transfer::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(Transfer::_deserializer<x10::lang::Ref>);

void Transfer::_serialize_body(x10aux::serialization_buffer& buf, x10aux::addr_map& m) {
    x10::lang::Ref::_serialize_body(buf, m);
    
}

void Transfer::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Ref::_deserialize_body(buf);
    
}

x10aux::RuntimeType Transfer::rtt;
void Transfer::_initRTT() {
    rtt.canonical = &rtt;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Ref>()};
    rtt.init(&rtt, "Transfer", 1, parents, 0, NULL, NULL);
}

extern "C" { const char* LNMAP__Transfer_cc = "N{\"Transfer.cc\"} F{0:\"/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translatedbenchs/hand/mapreduce/generic/MapReduceLauncher.x10\",1:\"Transfer\",2:\"transfer\",3:\"T{_self309.x10.lang.Object#location==here}\",4:\"T\",5:\"FMGL(T)\",6:\"this\",7:\"\",8:\"_constructor\",9:\"void\",} L{13->0:5,15->0:4,} M{5 1.2(5)->3 1.2(4);9 1.8()->7 1.6();}"; }

extern "C" { const char* LNMAP__Transfer_h = "N{\"Transfer.h\"} F{0:\"/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translatedbenchs/hand/mapreduce/generic/MapReduceLauncher.x10\",} L{8->0:6,11->0:7,} M{}"; }
