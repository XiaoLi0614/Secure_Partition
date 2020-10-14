#include <MapReduceLauncher.h>


#include "MapReduceLauncher.inc"

void MapReduceLauncher::_instance_init() {
    _I_("Doing initialisation for class: MapReduceLauncher");
    
}


//#line 222 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
#include <x10/runtime/Runtime.h>
#include <x10aux/bootstrap.h>
extern "C" { int main(int ac, char **av) { return x10aux::template_main<x10::runtime::Runtime,MapReduceLauncher>(ac,av); } }

void MapReduceLauncher::main(x10aux::ref<x10::lang::Rail<x10aux::ref<x10::lang::String> > > id5) {
    
    //#line 224 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10aux::ref<Timer> timer = Timer::_make();
    
    //#line 225 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10aux::nullCheck(timer)->start();
    
    //#line 230 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10aux::nullCheck(timer)->stop();
    
    //#line 231 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10aux::nullCheck(x10::io::Console::FMGL(OUT__get)())->x10::io::Printer::println(
      x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Ref> >(x10::lang::String::Lit("MapReduce\n") +
      x10::lang::String::Lit("Wall-clock time: ") +
      x10aux::safe_to_string(x10aux::nullCheck(timer)->readTimer()) +
      x10::lang::String::Lit(" secs.")));
}

//#line 189 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
void MapReduceLauncher::_constructor() {
    this->x10::lang::Ref::_constructor();
    
}x10aux::ref<MapReduceLauncher> MapReduceLauncher::_make(
   ) {
    x10aux::ref<MapReduceLauncher> this_ = new (x10aux::alloc<MapReduceLauncher>()) MapReduceLauncher();
    this_->_constructor();
    return this_;
}


const x10aux::serialization_id_t MapReduceLauncher::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(MapReduceLauncher::_deserializer<x10::lang::Ref>);

void MapReduceLauncher::_serialize_body(x10aux::serialization_buffer& buf, x10aux::addr_map& m) {
    x10::lang::Ref::_serialize_body(buf, m);
    
}

void MapReduceLauncher::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Ref::_deserialize_body(buf);
    
}

x10aux::RuntimeType MapReduceLauncher::rtt;
void MapReduceLauncher::_initRTT() {
    rtt.canonical = &rtt;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Ref>()};
    rtt.init(&rtt, "MapReduceLauncher", 1, parents, 0, NULL, NULL);
}

extern "C" { const char* LNMAP__MapReduceLauncher_cc = "N{\"MapReduceLauncher.cc\"} F{0:\"/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translatedbenchs/hand/mapreduce/generic/MapReduceLauncher.x10\",1:\"MapReduceLauncher\",2:\"main\",3:\"x10.lang.Void\",4:\"x10.lang.Rail[x10.lang.String]\",5:\"void\",6:\"x10aux::ref<x10::lang::Rail<x10aux::ref<x10::lang::String> > >\",7:\"this\",8:\"\",9:\"_constructor\",} L{34->0:235,20->0:224,23->0:225,37->0:189,26->0:230,29->0:231,13->0:222,} M{5 1.9()->8 1.7();5 1.2(6)->3 1.2(4);}"; }
