--diego
f1
  $ sometimes ((# begin (range 0 0.95 $ slow 40 $ sine)). (# end (range 0.05 1 $ slow 40 $ sine)))
  $ sometimes (# crush 16) $ fast 4 $ note (piraScale "diat4v2" "[0, -8?] 5 [2 10 11 18]/5")
  # s "<ade casio, wv3, 808*16>"
  # n "<[0, 1 3]>"
  # begin "<0 0.5>" # end "<0.1 0.3 0.7>"
  # gain (range 0.3 0.7 $ perlin)

--alex
f2 $ every 9 (# vowel "a") $ sometimes (# speed "-1 -0.5") $ struct "1(<22 27 18>,32)" $ s "bev*32" # begin (range 0 0.95 $ slow 40 $ sine) # end (range 0.05 1 $ slow 40 $ sine) # pan (range 0.25 0.75 $ slow 3 $ perlin)


-- visuales
-- osc(10,2,1).mask(shape(6)).repeat().modulateScale(noise(0.4)).modulate(noise(0.1).modulate(osc(100, 100)).diff(osc(3,2,1).diff(o0))).scrollX(10, 1).mult(shape(3, () => a.fft[0]*2).color(0.5, 2)).blend(src(o0).repeat().saturate(1.2).diff(o0)).modulate(osc().rotate()).out()
