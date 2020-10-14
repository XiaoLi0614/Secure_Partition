#include <MapReduceLauncher__MyMapReducer.h>


#include "MapReduceLauncher__MyMapReducer.inc"

void MapReduceLauncher__MyMapReducer::_instance_init() {
    _I_("Doing initialisation for class: MapReduceLauncher__MyMapReducer");
    
}


//#line 201 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
x10aux::ref<x10::util::Set<x10::util::Pair<x10aux::ref<x10::lang::String>, x10_int> > >
  MapReduceLauncher__MyMapReducer::map(
  x10aux::ref<x10::lang::String> line) {
    
    //#line 202 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10aux::ref<x10::lang::String> currentLine = line;
    
    //#line 203 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    x10aux::ref<MapReduceLauncher__HashSet<x10::util::Pair<x10aux::ref<x10::lang::String>, x10_int> > > set =
      MapReduceLauncher__HashSet<x10::util::Pair<x10aux::ref<x10::lang::String>, x10_int> >::_make();
    
    //#line 204 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    currentLine += x10::lang::String::Lit(" ");
    
    //#line 205 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    while (((line->length()) > (((x10_int)0)))) {
        
        //#line 206 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        x10_int index = (currentLine)->indexOf(x10::lang::String::Lit(" "));
        
        //#line 207 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        if (((index) <= (((x10_int)0)))) {
            
            //#line 208 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
            break;
        }
        
        //#line 209 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        x10aux::ref<x10::lang::String> word = (currentLine)->substring(((x10_int)0), ((index) + (((x10_int)1))));
        
        //#line 210 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        x10aux::nullCheck(set)->add(x10::util::Pair_methods<x10aux::ref<x10::lang::String>, x10_int>::_make(word,
                                                                                                            ((x10_int)1)));
        
        //#line 211 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
        currentLine = (currentLine)->substring(((index) + (((x10_int)1))), currentLine->length());
    }
    
    //#line 213 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    return set;
    
}

//#line 217 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
x10_int MapReduceLauncher__MyMapReducer::reduce(x10aux::ref<x10::util::Set<x10_int> > interms) {
    
    //#line 218 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
    return (((x10::lang::Object*)(((x10aux::ref<x10::lang::Object>)x10aux::placeCheck(x10aux::nullCheck(interms))).operator->()))->*(x10aux::findITable<x10::util::Set<x10_int> >(((x10aux::ref<x10::lang::Object>)interms)->_getITables())->size))();
    
}

//#line 197 "/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translated/hand/mapreduce/generic/MapReduceLauncher.x10"
void MapReduceLauncher__MyMapReducer::_constructor(
  ) {
    this->MapReducer<x10aux::ref<x10::lang::String>, x10aux::ref<x10::lang::String>, x10_int, x10_int>::_constructor();
    
}

void MapReduceLauncher__MyMapReducer::_serialize_body(x10aux::serialization_buffer& buf, x10aux::addr_map& m) {
    MapReducer<x10aux::ref<x10::lang::String>, x10aux::ref<x10::lang::String>, x10_int, x10_int>::_serialize_body(buf, m);
    
}

void MapReduceLauncher__MyMapReducer::_deserialize_body(x10aux::deserialization_buffer& buf) {
    MapReducer<x10aux::ref<x10::lang::String>, x10aux::ref<x10::lang::String>, x10_int, x10_int>::_deserialize_body(buf);
    
}

x10aux::RuntimeType MapReduceLauncher__MyMapReducer::rtt;
void MapReduceLauncher__MyMapReducer::_initRTT() {
    rtt.canonical = &rtt;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<MapReducer<x10aux::ref<x10::lang::String>, x10aux::ref<x10::lang::String>, x10_int, x10_int> >()};
    rtt.init(&rtt, "MapReduceLauncher$MyMapReducer", 1, parents, 0, NULL, NULL);
}

extern "C" { const char* LNMAP__MapReduceLauncher__MyMapReducer_cc = "N{\"MapReduceLauncher__MyMapReducer.cc\"} F{0:\"/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/2.Project/Project/src/x10/translatedbenchs/hand/mapreduce/generic/MapReduceLauncher.x10\",1:\"MapReduceLauncher$MyMapReducer\",2:\"map\",3:\"x10.util.Set[x10.util.Pair[x10.lang.String, x10.lang.Int]]\",4:\"x10.lang.String\",5:\"MapReduceLauncher__MyMapReducer\",6:\"x10aux::ref<x10::util::Set<x10::util::Pair<x10aux::ref<x10::lang::String>, x10_int> > >\",7:\"x10aux::ref<x10::lang::String>\",8:\"reduce\",9:\"x10.lang.Int\",10:\"x10.util.Set[x10.lang.Int]\",11:\"x10_int\",12:\"x10aux::ref<x10::util::Set<x10_int> >\",13:\"this\",14:\"\",15:\"_constructor\",16:\"void\",} L{34->0:207,38->0:208,65->0:197,37->0:208,41->0:209,13->0:201,44->0:210,49->0:212,18->0:202,48->0:211,21->0:203,54->0:214,52->0:213,25->0:204,57->0:217,62->0:219,28->0:205,31->0:206,60->0:218,} M{16 5.15()->14 1.13();6 5.2(7)->3 1.2(4);11 5.8(12)->9 1.8(10);}"; }
