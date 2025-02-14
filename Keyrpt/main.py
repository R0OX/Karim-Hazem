import kivy
kivy.require('2.1.0') 

from kivy.app import App
from kivy.uix.label import Label
from kivy.uix.button import Button
from kivy.uix.boxlayout import BoxLayout
from kivy.uix.textinput import TextInput
from kivy.lang import Builder
import string


Builder.load_file('keybg.kv')


    



char1 = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z']
n = "sample text"

class KEYrpt(App):
    

    def build(self):
        self.icon ="keylogo.png"
        global layout
        layout = BoxLayout(orientation='vertical')
        global button1
        global button2
        global button3
        global space1
        global space2
        space1 = Label(text='', size_hint=(.05,.025), pos_hint={'center_x': .5, 'center_y': .9})
        space2 = Label(text='', size_hint=(.05,.05), pos_hint={'center_x': .5, 'center_y': .9})
        title = Label(text='KEYrpt', size_hint=(.05,.05), pos_hint={'center_x': .5, 'center_y': .9},font_size=37,bold=True,underline=True)
        layout.add_widget(title)
        layout.add_widget(space1)
        button1 = Button(text='Ceaser', size_hint=(.5 , .025), pos_hint={'center_x': .5 , 'center_y': .8},font_size=28,background_color=[1,0,1,1],bold=True)
        button1.bind(on_press=lambda instance: self.on_press_button(instance, 1))
        layout.add_widget(button1)
        
        button2 = Button(text='Vigenere', size_hint=(.5 , .025), pos_hint={'center_x': .5 , 'center_y': .6},font_size=28,background_color=[1,0,1,1],bold=True)
        button2.bind(on_press=lambda instance: self.on_press_button(instance, 2))
        layout.add_widget(button2)
        
        button3 = Button(text='Mono_substitution', size_hint=(.5 , .025), pos_hint={'center_x': .5 , 'center_y': .4},font_size=28,background_color=[1,0,1,1],bold=True)
        button3.bind(on_press=lambda instance: self.on_press_button(instance, 3))
        layout.add_widget(button3)
        layout.add_widget(space2)

        global label
        label = Label(text='', size_hint=(1,.4), pos_hint={'center_x': .5, 'center_y': .9})
        global encrypt
        encrypt = Label(text='', size_hint=(1,.4), pos_hint={'center_x': .5, 'center_y': .9})
        global decrypt
        decrypt = Label(text='', size_hint=(1,.4), pos_hint={'center_x': .5, 'center_y': .9})
        global key
        key = Label(text='', size_hint=(1,.4), pos_hint={'center_x': .5, 'center_y': .9})
        global en
        en = Label(text='', size_hint=(1,.4), pos_hint={'center_x': .5, 'center_y': .9})
        global de
        de = Label(text='', size_hint=(1,.4), pos_hint={'center_x': .5, 'center_y': .9})
        global at
        at = Label(text='', size_hint=(1,.4), pos_hint={'center_x': .5, 'center_y': .9})
        global attack
        attack = Label(text='', size_hint=(1,.4), pos_hint={'center_x': .5, 'center_y': .9})

        return layout
        
    
    def on_press_button(self, instance, choice):
        global button1
        global button2
        global button3
        global label
        global encrypt
        global decrypt
        global attack
        global word
        global key
        global back
        word = Label(text='', size_hint=(1,.4), pos_hint={'center_x': .5, 'center_y': .9})
        self.root.remove_widget(button1)
        self.root.remove_widget(button2)
        self.root.remove_widget(button3)
        self.root.remove_widget(space1)
        self.root.remove_widget(space2)
        if choice == 1:
            self.root.remove_widget(label)
            self.root.remove_widget(encrypt)
            self.root.remove_widget(decrypt)
            self.root.remove_widget(word)
            self.root.remove_widget(key)
            label = Label(text='Ceaser', size_hint=(1,.1), pos_hint={'center_x': .5, 'center_y': .9},font_size=28,bold=True)
            encrypt = Button(text='Encrypt', size_hint=(.4 , .05), pos_hint={'center_x': .5 , 'center_y': .8},font_size=28,background_color=[1,0,1,1],bold=True)
            encrypt.bind(on_press=lambda instance: self.press(instance, 1))
            decrypt = Button(text='Decrypt', size_hint=(.4 , .05), pos_hint={'center_x': .5 , 'center_y': .8},font_size=28,background_color=[1,0,1,1],bold=True)
            decrypt.bind(on_press=lambda instance: self.press(instance, 2))
            attack = Button(text='Break', size_hint=(.4 , .05), pos_hint={'center_x': .5 , 'center_y': .8},font_size=28,background_color=[1,0,1,1],bold=True)
            attack.bind(on_press=lambda instance: self.press(instance, 3))
            word = TextInput(text=n,size_hint=(1,.05),  halign="left", font_size=24, pos_hint={'center_x': .5 , 'center_y': .5})
            key = TextInput(text="3",size_hint=(.5,.025),  halign="left", font_size=24, pos_hint={'center_x': .5 , 'center_y': .5},multiline=False,input_filter="int")
            back = Button(text='<-Back', size_hint=(.2 , .025), pos_hint={'center_x': .5 , 'center_y': .8},font_size=28,background_color=[1,0,1,1],bold=True)
            back.bind(on_press=lambda instance: self.press(instance, 10))
        
        elif choice == 2:
            self.root.remove_widget(label)
            self.root.remove_widget(encrypt)
            self.root.remove_widget(decrypt)
            self.root.remove_widget(word)
            self.root.remove_widget(key)
            label = Label(text='Vigenere', size_hint=(1,.1), pos_hint={'center_x': .5, 'center_y': .9},font_size=28,bold=True)
            encrypt = Button(text='Encrypt', size_hint=(.4 , .05), pos_hint={'center_x': .5 , 'center_y': .8},font_size=28,background_color=[1,0,1,1],bold=True)
            encrypt.bind(on_press=lambda instance: self.press(instance, 4))
            decrypt = Button(text='Decrypt', size_hint=(.4 , .05), pos_hint={'center_x': .5 , 'center_y': .8},font_size=28,background_color=[1,0,1,1],bold=True)
            decrypt.bind(on_press=lambda instance: self.press(instance, 5))
            attack = Button(text='Break from -up to 5 letters key', size_hint=(.4 , .05), pos_hint={'center_x': .5 , 'center_y': .8},background_color=[1,0,1,1],bold=True)
            attack.bind(on_press=lambda instance: self.press(instance, 8))
            word = TextInput(text=n,size_hint=(1,.05),  halign="left", font_size=24, pos_hint={'center_x': .5 , 'center_y': .5})
            key = TextInput(text="key",size_hint=(.5,.025),  halign="left", font_size=24, pos_hint={'center_x': .5 , 'center_y': .5},multiline=False)
            back = Button(text='<-Back', size_hint=(.2 , .025), pos_hint={'center_x': .5 , 'center_y': .8},font_size=28,background_color=[1,0,1,1],bold=True)
            back.bind(on_press=lambda instance: self.press(instance, 10))
            
        elif choice == 3:
            self.root.remove_widget(label)
            self.root.remove_widget(encrypt)
            self.root.remove_widget(decrypt)
            self.root.remove_widget(word)
            self.root.remove_widget(key)
            label = Label(text='Mono_substitution', size_hint=(1,.1), pos_hint={'center_x': .5, 'center_y': .9},font_size=28,bold=True)
            encrypt = Button(text='Encrypt', size_hint=(.4 , .05), pos_hint={'center_x': .5 , 'center_y': .8},font_size=28,background_color=[1,0,1,1],bold=True)
            encrypt.bind(on_press=lambda instance: self.press(instance, 6))
            decrypt = Button(text='Decrypt', size_hint=(.4 , .05), pos_hint={'center_x': .5 , 'center_y': .8},font_size=28,background_color=[1,0,1,1],bold=True)
            decrypt.bind(on_press=lambda instance: self.press(instance, 7))
            attack = Button(text='Analyze', size_hint=(.4 , .05), pos_hint={'center_x': .5 , 'center_y': .8},font_size=28,background_color=[1,0,1,1],bold=True)
            attack.bind(on_press=lambda instance: self.press(instance, 9))
            word = TextInput(text=n,size_hint=(1,.05),  halign="left", font_size=26, pos_hint={'center_x': .5 , 'center_y': .5})
            key = TextInput(text="helmrdstzkoyivpjnafwbqxugc",size_hint=(.5,.025),  halign="left", font_size=26, pos_hint={'center_x': .5 , 'center_y': .5},multiline=False)
            back = Button(text='<-Back', size_hint=(.2 , .025), pos_hint={'center_x': .5 , 'center_y': .8},font_size=28,background_color=[1,0,1,1],bold=True)
            back.bind(on_press=lambda instance: self.press(instance, 10))
        self.root.add_widget(label)
        self.root.add_widget(word)
        self.root.add_widget(key)
        self.root.add_widget(encrypt)
        self.root.add_widget(decrypt)
        self.root.add_widget(attack)
        self.root.add_widget(space1)
        self.root.add_widget(back)
        

    def press(self , instance , c):
            global en 
            global de
            global at
            global key
            global string
            if c == 1:
                self.root.remove_widget(en)
                self.root.remove_widget(de)
                self.root.remove_widget(at)
                text = word.text
                t = text.upper()
                n2 = ""
                for i in t:
                    if i in char1:
                        x = char1.index(i)
                        indx = x + int(key.text)
                        if indx < 26:
                            let = char1[indx]
                            n2 = n2 + let
                        else:
                            indx -= 26
                            let = char1[indx]
                            n2 = n2 + let
                    else:
                        n2 = n2 + i
                
                en = TextInput(text=n2, size_hint=(.5,.025), pos_hint={'center_x': .5, 'center_y': .9},readonly=True)#d#
                self.root.add_widget(en)

            if c == 2:
                self.root.remove_widget(en)
                self.root.remove_widget(de)
                self.root.remove_widget(at)
                text = word.text
                t = text.upper()
                n2 = ""
                for i in t:
                    if i in char1:
                        x = char1.index(i)
                        indx = x - int(key.text)
                        if indx < 26:
                            let = char1[indx]
                            n2 = n2 + let
                    else:
                        n2 = n2 + i
                de = TextInput(text=n2, size_hint=(.5,.025), pos_hint={'center_x': .5, 'center_y': .9},readonly=True)
                self.root.add_widget(de)
            
            if c == 3:
                self.root.remove_widget(en)
                self.root.remove_widget(de)
                self.root.remove_widget(at)
                text = word.text
                t = text.upper()
                n=""
                for m in range(1,27):
                    n2 = ""
                    for i in t:
                        if i in char1:
                            y = char1.index(i)
                            let = char1[y-m]
                            n2 = n2 + let
                        if i not in char1:
                            let = i
                            n2 = n2 + let
                    n = n +", "+ str(m) +'-'+ n2 
                at = TextInput(text=n, size_hint=(.5,.5), pos_hint={'center_x': .5, 'center_y': .9},font_size=18,)
                self.root.add_widget(at)

                        
            if c == 4:
                self.root.remove_widget(en)
                self.root.remove_widget(de)
                self.root.remove_widget(at)
                indx = []
                text = word.text
                t = text.upper()
                k = key.text
                k1 = k.upper()
                w =""
                for k2 in k1:
                    indx.append(k2)
                num = 0
                for i in t:
                    if i in char1:
                        y = char1.index(i)
                        z = indx[num]
                        x = char1.index(z)
                        if x+y < 26:
                            let = char1[y+x]
                            w = w + let
                            num+=1
                        else:
                            let = char1[x+y-26]
                            w = w + let
                            num+=1
                    if i not in char1:
                        let = i
                        w = w + let
                    if num == len(indx):
                        num=0
                en = TextInput(text=w, size_hint=(.5,.025), pos_hint={'center_x': .5, 'center_y': .9},readonly=True)
                self.root.add_widget(en)

            if c == 5:
                self.root.remove_widget(en)
                self.root.remove_widget(de)
                self.root.remove_widget(at)
                indx = []
                text = word.text
                t = text.upper()
                k = key.text
                k1 = k.upper()
                w =""
                for k2 in k1:
                    indx.append(k2)
                num = 0
                for i in t:
                    if i in char1:
                        y = char1.index(i)
                        z = indx[num]
                        x = char1.index(z)
                        if x-y < 26:
                            let = char1[y-x]
                            w = w + let
                            num+=1
                        else:
                            let = char1[x-y+26]
                            w = w + let
                            num+=1
                    if i not in char1:
                        let = i
                        w = w + let
                    if num == len(indx):
                        num=0
                de = TextInput(text=w, size_hint=(.5,.025), pos_hint={'center_x': .5, 'center_y': .9},readonly=True)
                self.root.add_widget(de)

            if c == 8:
                file = open("words.txt","r")
                self.root.remove_widget(en)
                self.root.remove_widget(de)
                self.root.remove_widget(at)
                text = word.text
                b = text.upper()
                l = 0
                n=""
                x=file.readlines()
                l=0
                for line in x:
                        k1 = line.rstrip()
                        if len(k1) <= 5:
                                k2 = k1.upper()
                                w =""
                                l+= 1
                                indx = []
                                num=0
                                for k3 in k2:
                                        indx.append(k3)
                                for i in b:
                                        if i in char1:
                                                u = char1.index(i)
                                                o = indx[num]
                                                p = char1.index(o)
                                        if p-u < 26:
                                                let = char1[u-p]
                                                w = w + let
                                                num+=1
                                        else:
                                                let = char1[u-p+26]
                                                w = w + let
                                                num+=1
                                        if i not in char1:
                                                let = i
                                                w = w + let
                                        if num == len(indx):
                                                num=0
                                n=n+str(l)+"-key="+k1+", decryption="+w+","
                at = TextInput(text=n, size_hint=(.5,.5), pos_hint={'center_x': .5, 'center_y': .9},font_size=24)
                self.root.add_widget(at)
                file.close

            if c == 6:
                self.root.remove_widget(en)
                self.root.remove_widget(de)
                self.root.remove_widget(at)
                text = word.text
                k = key.text
                for i in text:
                    if i.isupper():
                        alphabet = string.ascii_uppercase
                    elif i.islower():
                        alphabet = string.ascii_lowercase
                    table = str.maketrans(alphabet, k)
                    t = text.translate(table)
                en = TextInput(text=t, size_hint=(.5,.025), pos_hint={'center_x': .5, 'center_y': .9},readonly=True)
                self.root.add_widget(en)

            if c == 7:
                self.root.remove_widget(en)
                self.root.remove_widget(de)
                self.root.remove_widget(at)
                text = word.text
                k = key.text
                alphabet = string.ascii_lowercase
                table = str.maketrans(k, alphabet)
                t = text.translate(table)
                de = TextInput(text=t, size_hint=(.5,.025), pos_hint={'center_x': .5, 'center_y': .9},readonly=True)
                self.root.add_widget(de)

            if c == 9:
                self.root.remove_widget(en)
                self.root.remove_widget(de)
                self.root.remove_widget(at)
                k = word.text
                letter_count = {}
                for letter in k:
                    if letter not in letter_count:
                        letter_count[letter] = 1
                    else:
                        letter_count[letter] += 1
                converted = str()
                for l in letter_count:
                    converted += "'"+l+"'" + "= " + str(letter_count[l]) + "\n"
                at = TextInput(text=converted, size_hint=(.5,.5), pos_hint={'center_x': .5, 'center_y': .9},font_size=24,readonly=True)
                self.root.add_widget(at)
        
            if c == 10:
                self.root.remove_widget(label)
                self.root.remove_widget(encrypt)
                self.root.remove_widget(attack)
                self.root.remove_widget(decrypt)
                self.root.remove_widget(word)
                self.root.remove_widget(key)
                self.root.remove_widget(back)
                self.root.remove_widget(en)
                self.root.remove_widget(de)
                self.root.remove_widget(at)
                self.root.remove_widget(space1)
                layout.add_widget(space1)
                layout.add_widget(button1)
                layout.add_widget(button2)
                layout.add_widget(button3)
                layout.add_widget(space2)


if __name__ == '__main__':
    KEYrpt().run()