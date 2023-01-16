import qualified Data.Map as Map
import Sound.Tidal.Scales
import Data.List
import Data.Maybe

aliases = [("3oo5x9t1i7i15i19i21", "pepito")]

addAliases' k alias scales = Map.insert alias (fromMaybe [2.005319830023106,2.941349974038373,4.674028886060471] (Map.lookup k scales)) scales

addAliases aliases scales = Map.toList $ Map.foldrWithKey addAliases' (Map.fromList scales) (Map.fromList aliases)

piraScales' = addAliases aliases piraScales

piraScaleList = mapM_ print $  reverse $ sort $ map fst piraScales'

piraScale = getScale piraScales' 

