import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Swipe_Image extends PApplet {

int numFrames = 6;  // The number of frames in the animation
int frame = 0;

PImage[] images = new PImage[numFrames];

float offsetX = 0;
float easing = 0.25f;
float imgX;
boolean locked = false;
boolean next = false;
boolean prev = false;

float dx = 0;
float targetX = 0;

//ParticleSystem ps;

public void setup()
{
  
  textAlign(CENTER, CENTER);
  //background(0);
  imgX = width/2;
  imageMode(CENTER);

  images[0]  = loadImage("0.png");
   images[1]  = loadImage("1.png");
   images[2]  = loadImage("2.png"); 
   images[3]  = loadImage("3.png");
   images[4]  = loadImage("4.png"); 
   images[5]  = loadImage("5.png");
   
   image(images[0], width/2, height/2, 713, 800);
}

public void draw()
{
  //background(0);
  frame = (frame) % numFrames;  // Use % to cycle through frames
 
  translate(400,400,0);
  rotateX(map(mouseY,0,height,-PI/30,PI/30));
  rotateY(map(mouseX,0,width,-PI/30,PI/30));
  translate(-400,-400,0);

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
  image(images[frame], width/2, height/2, 713, 800);
}

public void swipe_event(String swipe)
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

public void nextImage() {
  if (frame<5)
  { 
    frame++;
  } else frame = 0;
}

public void prevImage() {
  if (frame>0)
  { 
    frame--;
  } else frame = 5;
}
int px, py;

int min_dist_x = 80;
int max_dist_y = 30;
int min_duration = 250;
int max_duration = 1000;

boolean swiping;
String swipe;
int swipe_start;

public void check_swipe(int x, int y)
{
  String swipe_return = null;
  int dist_x = abs(x - px);
  int dist_y = abs(y - py);
  
  if (dist_x > min_dist_x && dist_y < max_dist_y)
  {
    if (swiping == false)
    {
      swiping = true;
      swipe_start = millis();
    }
    
    if (x > px)
      swipe = "right";
    else
      swipe = "left";
  }
  else
  {
    if (swiping == true)
    {
      swiping = false;
      swipe_event(swipe);
    }
  }
  
  px = x;
  py = y;
}
  public void settings() {  size(713, 800, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "Swipe_Image" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
