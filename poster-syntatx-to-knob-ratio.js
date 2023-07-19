
p1 = new P5()
p2 = new P5()

p1.clear()
p1.textSize(100);
p1.fill( 199, 0, 57 );
p1.stroke(10,10,10);
p1.strokeWeight(2);
p1.textFont('monospace');
p1.text('Syntax to Knob', p1.displayWidth/2 - 500, p1.displayHeight/2);
p1.fill( 199, 199, 57 );
p1.text('Ratio', p1.displayWidth/2 - 200, p1.displayHeight/2 + 60);




p1.hide()
p2.hide()
s1.init({src:p1.canvas})
s2.init({src:p2.canvas})

s3.initImage("https://magfoto.xyz/pix/bioproc.jpg")
s2.initImage("https://live.staticflickr.com/65535/52961122037_692c1826a4_b.jpg")

src(s3).blend(src(s2)).modulate(src(o0), 0.5).layer(src(s1).scale(1.5)).diff(src(s1).repeat(2).modulate(noise()).diff(src(o0))).posterize(5).saturate(3).diff(src(s2)).mult(src(s3), 0.2).modulateRotate(src(o0).scale(4), 0.5).diff(shape(3, 0.4, 0.2).scrollY(0.5).saturate(10)).blend(src(s1).repeat(5).scale(5), 0.4).modulateScale(src(o0).repeat(10).kaleid(20)).saturate(1.4).out()



///
//
//
p1 = new P5()
p2 = new P5()

p1.clear()
p1.textSize(100);
p1.fill( 199, 0, 57 );
p1.stroke(10,10,10);
p1.strokeWeight(2);
p1.textFont('monospace');
p1.text('Syntax to Knob', p1.displayWidth/2 - 500, p1.displayHeight/2);
p1.fill( 199, 199, 57 );
p1.text('Ratio', p1.displayWidth/2 - 200, p1.displayHeight/2 + 60);




p1.hide()
p2.hide()
s1.init({src:p1.canvas})
s2.init({src:p2.canvas})

s3.initImage("https://magfoto.xyz/pix/bioproc.jpg")
s2.initImage("https://live.staticflickr.com/65535/52961122037_692c1826a4_b.jpg")

src(s3).blend(src(s2)).modulate(src(o0), 0.5).layer(src(s1).scale(1.5)).diff(src(s1).repeat(2).modulate(noise()).diff(src(o0))).posterize(5).saturate(3).diff(src(s2)).mult(src(s3), 0.2).modulateRotate(src(o0).scale(4), 0.5).diff(src(s3)).diff(shape(3, 0.4, 0.2).scrollY(0.5).saturate(10)).blend(src(s1).repeat(5).scale(5), 0.4).modulateScale(src(o0).repeat(10).kaleid(20)).saturate(1.4).modulate(src(o0).modulate(src(s3), 0.2), 0).out()



//
p1 = new P5()
p2 = new P5()

p1.clear()
p1.textSize(100);
p1.fill( 199, 0, 57 );
p1.stroke(10,10,10);
p1.strokeWeight(2);
p1.textFont('monospace');
p1.text('Syntax to Noise', p1.displayWidth/2 - 500, p1.displayHeight/2);
p1.fill( 199, 199, 57 );
p1.text('Ratio', p1.displayWidth/2 - 200, p1.displayHeight/2 + 60);


p1.hide()
p2.hide()
s1.init({src:p1.canvas})
s2.init({src:p2.canvas})

s3.initImage("https://magfoto.xyz/pix/bioproc.jpg")
s2.initImage("https://live.staticflickr.com/65535/52961122037_692c1826a4_b.jpg")

src(s3).blend(src(s2)).modulate(src(o0), 0.1).layer(src(s1).scale(1.5)).diff(src(s1).repeat(2).modulate(noise()).diff(src(o0))).posterize(5).saturate(3).diff(src(s2)).out()


//
p1 = new P5()
p2 = new P5()

p1.clear()
p1.textSize(100);
p1.fill( 199, 0, 57 );
p1.stroke(10,10,10);
p1.strokeWeight(2);
p1.textFont('monospace');
p1.text('Synth', p1.displayWidth/2 - 500, p1.displayHeight/2 - 200);
p1.fill( 199, 199, 57 );
p1.text('Syntax', p1.displayWidth/2 - 200, p1.displayHeight/2 + 100);




p1.hide()
p2.hide()
s1.init({src:p1.canvas})
s2.init({src:p2.canvas})

s3.initImage("https://magfoto.xyz/pix/bioproc.jpg")
s2.initImage("https://lh3.googleusercontent.com/drive-viewer/AITFw-z90FU9S5ptjH4jPkA5oghk7kflQusgPdUFaYE597Fwlwbg668TXKZRmDpAVpu_strpYp6m4sxyzA8pOCMrb9S_XICIZA=s1600")

src(s3).blend(src(s2)).modulate(src(o0), 0.1).layer(src(s1).scale(1.5)).diff(src(s1).repeat(2).modulate(noise()).diff(src(o0))).posterize(5).saturate(3).diff(src(s2)).out()
