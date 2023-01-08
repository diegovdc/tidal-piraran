# Pirarán setup

In this repo you will find SuperCollider, Haskell and Tidal files to play with the materials that Pirarán has developed for the Side B of Algorithmic Acid Music. 

## Booty

Add this snippets at the end of the ```Boot.hs``` file provided in this repo changing the 

```
:script /path/to/this/Repo/piraran/tidal-piraran/tunings/main.hs
:script /path/to/this/Repo/piraran/tidal-piraran/PiraranSuperCollider/funcas.tidal
```

Then, in Atom (if you are using Atom), go to File -> Preferences -> Packages -> TidalCycles.

In the section of settings change the field called 'Boot Tidal Path' to the file path of ```Boot.hs``` provided here.

## SuperDirt init

To start SuperDirt open the file ```bootPiraran.scd``` that can be found in the folder PiraranSuperCollider in this repo. Then select all and evaluate.

## SynthDefs

A library of wavetables will be loaded after booting the server. The li9brary was adapted from this website: https://www.adventurekid.se/akrt/waveforms/

All open source baby!

To see the wave catalogue and its organisation use the following code:

```~postOscs```

This are all keys of a Dictionary so this will post the min and max range of buffer numbers that contain such wavetables:

```~osciladores.at(\_pluckalgo)```

Plot the first wavetable of the range above like this:

```~waveArray.flatten[~osciladores.at(\_pluckalgo).min].plot```

Test the Synths in SuperCollider:

```(instrument: \wpad, wave: 1500).play```

```(instrument: \wlpfpad, wave: 1500).play```

```(instrument: \whpfpad, wave: 1500).play```

```(instrument: \wchpad).play```

```(instrument: \wvpad).play```

```(instrument: \wv3pad).play```

```(instrument: \wperc, wave: 1500).play```

```(instrument: \wlpfperc, wave: 1500).play```

```(instrument: \whpfperc, wave: 1500).play```

```(instrument: \wchpad).play```

```(instrument: \wvperc).play```

```(instrument: \wv3perc).play```
