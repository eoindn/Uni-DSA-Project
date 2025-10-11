ResponseUnit
├─ id (String)              → "UNIT001"
├─ type (String)            → "AMBULANCE"
└─ currentLocation (Location)
├─ id (String)       → "LOC001"
├─ name (String)     → "London"
├─ latitude (double)
└─ longitude (double)

Incident
├─ id (String)              → "INC001"
├─ description (String)     → "Fire"
└─ location (Location)
├─ id (String)       → "LOC002"
└─ name (String)     → "Manchester"

Connection (between locations)
├─ destinationId (String)   → "LOC002"
├─ distance (double)
└─ travelTime (double)

1. Use IntelliJ's Built-in Tools (Game Changers!)Ctrl + Click (or Cmd + Click on Mac)
   Click on ANY method name and it jumps to the definition:javadouble distance = network.calculateDistance(unit, incident);
   //                        ↑ Ctrl+Click hereInstantly shows you what calculateDistance() does!Ctrl + Q (Quick Documentation)
   Hover over a method and press Ctrl + Q:
   javaunit.isAvailable()  // Hover + Ctrl+Q shows what it returnsShows you the method signature and any JavaDoc comments.Ctrl + Space (Auto-complete)
   Type unit. and press Ctrl + Space - shows ALL available methods:
   javaunit.
   // Shows: getId(), getType(), getLocation(), isAvailable(), setAvailable()...You don't need to remember - IntelliJ reminds you!