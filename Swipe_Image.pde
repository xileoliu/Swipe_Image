int numFrames = 6;  // The number of frames in the animation
int frame = 0;

PImage[] images = new PImage[numFrames];

float offsetX = 0;
float easing = 0.25;
float imgX;
boolean locked = false;
boolean next = false;
boolean prev = false;

float dx = 0;
float targetX = 0;

//ParticleSystem ps;

void setup()
{
  size(713, 800, P3D);
  textAlign(CENTER, CENTER);
  //background(0);
  imgX = width/2;
  imageMode(CENTER);

  for (int i = 0; i < numFrames; i++)
  {
    images[i] = loadImage(i+".png");
    images[i].resize(0, height);
    //images[1]  = loadImage("1.png");
    //images[2]  = loadImage("2.png"); 
    //images[3]  = loadImage("3.png");
    //images[4]  = loadImage("4.png"); 
    //images[5]  = loadImage("5.png");
  }

  image(images[0], width/2, height/2, 713, 800);
}

void draw()
{
  background(0);
  frame = (frame) % numFrames;  // Use % to cycle through frames

  //pushMatrix();
  translate(width/2, height/2, 0);
  //float zoom = map(mouseX, 0, width, 1, 1.05);
  //scale(zoom);
  rotateX(map(mouseY, 0, height, -PI/90, PI/90));
  rotateY(map(mouseX, 0, width, -PI/90, PI/90));
  //popMatrix();
  translate(-width/2, -height/2, 0);

  check_swipe(mouseX, mouseY);
  /*
  if (swipe == "left")
   {
   println("swipe left");
   }
   else if (swipe == "right")
   {
   println("swipe right");
   }
   */
  textSize(72*(frame+1));
  //text(frame+1, imgX, height/2);
  //images[frame].resize(0, height);
  //image(images[frame], width/2, height/2, 713, 800);
  image(images[frame], width/2, height/2);
}

void swipe_event(String swipe)
{
  println(swipe);

  if (swipe == "left") {
    prevImage();
    println("frame: " + frame);
  } else if (swipe == "right") {
    nextImage();
    println("frame: " + frame);
  }
  //image(images[frame], width/2, height/2, 713, 800);
}

void nextImage() {
  if (frame<5)
  { 
    frame++;
  } else frame = 0;
}

void prevImage() {
  if (frame>0)
  { 
    frame--;
  } else frame = 5;
}