import pyglet
from pyglet.gl import *

def draw_text(text, x, y, font_size=12, color=(255, 255, 255)):
    label = pyglet.text.Label(text, font_name='Arial', font_size=font_size, x=x, y=y, color=color)
    label.draw()

def draw_birthday_card(name):
    window = pyglet.window.Window(width=800, height=600)

    @window.event
    def on_draw():
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
        glLoadIdentity()
        gluPerspective(60, 1, 1, 100)
        glTranslatef(0, 0, -5)
        glRotatef(0, 1, 0, 0)
        glRotatef(0, 0, 1, 0)

        glColor3f(1, 1, 1)
        glBegin(GL_QUADS)
        glVertex3f(-1, -1, 0)
        glVertex3f(1, -1, 0)
        glVertex3f(1, 1, 0)
        glVertex3f(-1, 1, 0)
        glEnd()

        draw_text("Happy Birthday,", 0, 0, 24, (255, 0, 0))
        draw_text(name, 0, -20, 24, (0, 0, 255))
        draw_text("Wishing you all the best!", 0, -40, 12, (0, 0, 0))

    pyglet.app.run()

# Menggunakan fungsi draw_birthday_card untuk mengucapkan selamat ulang tahun kepada Yosep
draw_birthday_card("Yosep")