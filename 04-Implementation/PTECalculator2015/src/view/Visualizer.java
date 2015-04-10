package view;

import domain.Vinkel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Visualizer extends Component implements MouseListener, MouseMotionListener {
   private static int GRID_SIZE = 50;
   private static int RIGHT_ANGLE_SIZE = 15;
   private static Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 20);

   private Point origin;
   private Vinkel vinkel;

   private boolean draggable;
   private Point dragFrom, dragOrigin;

   public Visualizer() {
      setBackground(Color.WHITE);
      addMouseListener(this);
      addMouseMotionListener(this);
   }

   private static double toRadians(double degrees) {
      return degrees / 180 * Math.PI;
   }

   private static double square(double n) {
      return n * n;
   }

   private static void styleGrid(Graphics2D g2) {
      g2.setColor(Color.GRAY);
      g2.setStroke(new BasicStroke());
   }

   private static void styleAxes(Graphics2D g2) {
      g2.setColor(Color.DARK_GRAY);
      g2.setStroke(new BasicStroke(2));
   }

   private static void styleCircle(Graphics2D g2) {
      g2.setColor(Color.GRAY);
      g2.setStroke(new BasicStroke());
   }

   private static void styleArm(Graphics2D g2) {
      g2.setColor(Color.BLUE);
      g2.setStroke(new BasicStroke(10));
   }

   private static void styleTriangle(Graphics2D g2) {
      g2.setColor(Color.RED);
      g2.setStroke(new BasicStroke(2));
   }

   private static void styleRightAngle(Graphics2D g2) {
      g2.setColor(Color.RED);
      g2.setStroke(new BasicStroke(1.5f));
   }

   private static void styleLabels(Graphics2D g2) {
      g2.setColor(Color.BLACK);
   }

   public void setOrigin(Point origin) {
      this.origin = new Point(origin);
      repaint();
   }

   public void setVinkel(Vinkel vinkel) {
      this.vinkel = vinkel;
      repaint();
   }

   public boolean isDraggable() {
      return draggable;
   }

   public void setDraggable(boolean draggable) {
      this.draggable = draggable;
   }

   @Override
   public void paint(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      g2.setFont(FONT);
      // Paint background
      g2.setColor(getBackground());
      g2.fillRect(0, 0, getWidth(), getHeight());

      if (origin == null)
         setOrigin(new Point(getWidth() / 2, getHeight() / 2));

      drawGrid(g2);
      drawAxes(g2);

      if (vinkel == null)
         return;

      double r = (getHeight() - GRID_SIZE) / 2;
      drawCircle(g2, r);
      drawTriangle(g2, r);
   }

   private void drawGrid(Graphics2D g2) {
      Dimension size = getSize();
      styleGrid(g2);

      // Draw vertical lines
      for (int x = origin.x % GRID_SIZE; x <= size.width; x += GRID_SIZE)
         g2.drawLine(x, 0, x, size.height);

      // Draw horizontal lines
      for (int y = origin.y % GRID_SIZE; y <= size.height; y += GRID_SIZE)
         g2.drawLine(0, y, size.width, y);
   }

   private void drawAxes(Graphics2D g2) {
      styleAxes(g2);
      // Draw x-axis
      g2.drawLine(0, origin.y, getWidth(), origin.y);
      // Draw y-axis
      g2.drawLine(origin.x, 0, origin.x, getHeight());
   }

   private void drawCircle(Graphics2D g2, double radius) {
      styleCircle(g2);
      int d = (int) (2 * radius);
      g2.drawOval((int) (origin.x - radius), (int) (origin.y - radius), d, d);
   }

   private void drawTriangle(Graphics2D g2, double radius) {
      // Draw arm
      // Normalize angle in degrees
      double deg = -(vinkel.tilVandret() ? vinkel.getGradtal() : 90 - vinkel.getGradtal());
      double angP = toRadians(deg);
      // Endpoint of arm
      Point p = new Point(
              (int) (Math.cos(angP) * radius) + origin.x,
              (int) (Math.sin(angP) * radius) + origin.y);
      styleArm(g2);
      g2.drawLine(origin.x, origin.y, p.x, p.y);

      // Skip the triangle if either force is zero
      if (deg % 90 == 0)
         return;

      // Draw Fn triangle edge
      styleTriangle(g2);
      g2.drawLine(origin.x, origin.y, p.x, p.y);

      // Draw Ft triangle edge
      double len = Math.sin(toRadians(-90 - deg)) * radius / Math.sin(angP);
      double angQ = angP + Math.PI / 2;
      // Endpoint
      Point q = new Point(
              (int) (Math.cos(angQ) * len) + origin.x,
              (int) (Math.sin(angQ) * len) + origin.y);
      g2.drawLine(origin.x, origin.y, q.x, q.y);

      // Draw Fdim triangle edge
      g2.drawLine(p.x, p.y, q.x, q.y);

      // Draw right angle
      len = Math.sqrt(2 * square(RIGHT_ANGLE_SIZE));
      boolean flip = (p.x < origin.x && p.y <= origin.y || p.x >= origin.x && p.y > origin.y);
      double angW = angP + Math.PI / 4 * (flip ? -1 : 1);
      Point w = new Point(
              (int) (Math.cos(angW) * len) + origin.x,
              (int) (Math.sin(angW) * len) + origin.y);
      styleRightAngle(g2);
      // Line parallel with Fn
      len = RIGHT_ANGLE_SIZE;
      g2.drawLine(
              (int) (Math.cos(angP) * len) + origin.x,
              (int) (Math.sin(angP) * len) + origin.y,
              w.x, w.y);
      // Line parallel with Ft
      len = RIGHT_ANGLE_SIZE * (flip ? -1 : 1);
      g2.drawLine(
              (int) (Math.cos(angQ) * len) + origin.x,
              (int) (Math.sin(angQ) * len) + origin.y,
              w.x, w.y);

      // Draw labels
      double rotation = -Math.PI / 2;
      double rotP = angP + rotation;
      double rotQ = angQ + rotation;
      styleLabels(g2);
      // Draw Fn
      len = 40 * (flip ? -1 : 1);
      g2.drawString("Fn",
              (float) (Math.cos(rotP) * len) + (origin.x + p.x) / 2f,
              (float) (Math.sin(rotP) * len) + (origin.y + p.y) / 2f);
      // Draw Ft
      len = -40;
      g2.drawString("Ft",
              (float) (Math.cos(rotQ) * len) + (origin.x + q.x) / 2f,
              (float) (Math.sin(rotQ) * len) + (origin.y + q.y) / 2f);
      // Draw Fdim
      g2.drawString("Fdim",
              p.x + (p.x < origin.x ? -55 : 15),
              (p.y + q.y) / 2);
   }

   @Override
   public void mousePressed(MouseEvent e) {
      if (!draggable)
         return;

      dragFrom = e.getPoint();
      dragOrigin = new Point(origin);
   }

   @Override
   public void mouseDragged(MouseEvent e) {
      if (!draggable)
         return;

      Point p = e.getPoint();
      origin.x = (p.x - dragFrom.x + dragOrigin.x);
      origin.y = (p.y - dragFrom.y + dragOrigin.y);
      repaint();
   }

   @Override
   public void mouseClicked(MouseEvent e) {
      // No-op
   }

   @Override
   public void mouseReleased(MouseEvent e) {
      // No-op
   }

   @Override
   public void mouseEntered(MouseEvent e) {
      // No-op
   }

   @Override
   public void mouseExited(MouseEvent e) {
      // No-op
   }

   @Override
   public void mouseMoved(MouseEvent e) {
      // No-op
   }
}
