#ASKHSH 1
from operator import *
P=0
M1=int(input("Please enter a positive number: "))
M2=int(input("Please enter another positive number: "))
while M2>0:
    if mod(M2,2)==1 or M2==1: # same as mod(Μ2,2)!=0(both do the job ,but the first is a better mathematical approach.)
        P=P+M1
        M1=M1*2
        M2=int(M2/2)
        """else:
                break is not needed."""
print(P)

#ASKHSH 2 a).
num1=float(input("Enter a number: "))
num2=float(input("Enter another number: "))
if num1>num2:
    print(num1, "is higher than ", num2, ".")
elif num2>num1:
    print(num2, "is higher than", num1, ".")
else:
    print("Error.Wrong input.")

#ASKHSH 2 b).
num1=float(input("Enter a number: "))
num2=float(input("Enter another number: "))
def find_max(a, b):
    """Δέχεται δύο αριθμούς επιλεγμένους από τον χρήστη και βρίσκει τον μέγιστο
 απο αυτούς χρησιμοποιώντας την in-built συνάρτηση max()."""
    return max(a, b)
if num1!=num2:
    print("The highest number out of the two you entered is",find_max(num1, num2))
else:
    print("Error.Wrong input.")

#ASKHSH 2 c).
num1=float(input("Enter a number: "))
num2=float(input("Enter another number: "))
num3=float(input("Enter another number: "))
num4=float(input("Enter another number: "))
def find_max_4(a, b, c, d):
    return max(max(a,b), max(c,d))
if num1!=num2!=num3!=num4:
    print("The highest number out of the four you entered is", find_max_4(num1, num2, num3, num4))
else:
    print("Error.Wrong input.")

#AKHSH 3 a).
height=input("Enter the triangle's height: ")
base=input("Enter the triangle's base: ")
if height>0 and base>0:
   result=(1/2)*base*height
   print("The are of the triangle with the given dimensions is", result, ".")
elif height<0:
    print("The value you have entered for the height of the triangle is invalid.")
elif base<0:
    print("The value you have entered for the base of the triangle is invalid.")
elif height==0 and base==0:
    print("The values you have entered do not correspond to lengths.")
elif height==0:
   print("Invalid input for height.")
elif base==0:
    print("Invalid input for base.")


#ASKHSH 3 b).
height=float(input("Enter the triangle's height: "))
base=float(input("Enter the triangle's base: "))
def Triangle_Area_1(h,b):
    """Βρίσκει το εμβαδόν ενός τριγώνου του οποίου γνωρίζουμε την βάση και το ύψος του."""
    if h>0 and b>0 :
        return (1/2)*b*h
    elif height<0:
        print("The value you have entered for the height of the triangle is invalid.")
    elif base<0:
        print("The value you have entered for the base of the triangle is invalid.")
    elif height==0 and base==0:
        print("The values you have entered do not correspond to lengths.")
    elif height==0:
        print("Invalid input for height.")
    elif base==0:
        print("Invalid input for base.")
print(Triangle_Area_1(height, base))
#ASKHSH 3 c).
height=float(input("Enter the triangle's height: "))
base=float(input("Enter the triangle's base: "))
width=float(input("Enter the pyramid's width.(Optional. If you want to work with a triangle, just type 0): "))
def Triangle_Area_2(h,b,w):
    """Βρίσκει το εμβαδόν ενός τριγώνου του οποίου γνωρίζουμε την βάση και το ύψος του, αλλά δέχεται και 3η μεταβλητή, το πλάτος. Στην συγκεκριμένη περίπτωση υπολογίζει τον όγκο της πυραμίδας που δημιουργείτε εντέλει. ."""
    if w==0:  
        if h>0 and b>0 :
            return (1/2)*b*h
        elif height<0:
            print("The value you have entered for the height of the triangle is invalid.")
        elif base<0:
            print("The value you have entered for the base of the triangle is invalid.")
        elif height==0 and base==0:
            print("The values you have entered do not correspond to lengths.")
        elif height==0:
            print("Invalid input for height.")
        elif base==0:
            print("Invalid input for base.")
    elif w!=0 and h>0 and b>0:
        return (w*h*b)/3
print(Triangle_Area_2(height, base, width))  

#ASKHSH 4 (+Bonus)
import random
input_nums=input("Please enter a number of integers separated by space: ")
list=input_nums.split()
list.sort() # sorted(list) or new_list=sorted(list)
num=int(input("Please enter an integer: "))
if len(list)!=0: # same as bool(list)==True
    for i in range(len(list)):
        list[i] = int(list[i])
    #This way we can make a list exclusively from user input.
    occurences=list.count(num)
    if num not in list: # same as occurences == 0
        print("The number you have entered is not in the list.")
    else:
        if occurences<3:
            print("The number you entered does not appear 3 times in the list.")
        else:
            occured=0
            i=0
            while occured<3:
                i=list.index(num, i)
                occured+=1
                i+=1
            print(i)
else: #Bonus Segment
    print("No list given. One will be created randomly.")
    random_list=[]
    for i in range(1,21):
        random_num=random.randint(0,1000)
        random_list.append(random_num)
    print(random_list)
        
#ASKHSH 4 (Modified)
input_nums=input("Please enter a number of integers separated by space: ")
list=input_nums.split()
list.sort() # sorted(list) or new_list=sorted(list)
num=int(input("Please enter an integer: "))
if len(list)!=0: # same as bool(list)==True
    for i in range(len(list)):
        list[i] = int(list[i])
    #This way we can make a list exclusively from user input.
    occurences=list.count(num)
    if num not in list: # same as occurences == 0
        print("The number you have entered is not in the list.")
    else:
        positions=[] ; locations=[]
        if occurences<3:
            print("The number you entered does not appear 3 times in the list.")
        else:
            occured=0
            i=0           
            for occured in range(0,3):
                 i_new=list.index(num, i)
                 positions.append(i_new)
                 i=i_new +1
                 
        # Other version with WhileLoop.
            # while occured<3:
            #     i=list.index(num, i)
            #     positions.append(i)
            #     # Alternatevily locations.insert(occured, i)
            #     occured+=1
            #     if occured==3:
            #         break
            #     else:
            #         i+=1
         
            #print(i)
            print(list)
            print(positions)
            #print(locations)
            print(i_new)
     
