"""Conway\'s Game of Life."""

#---------------------- Askisi 1 ------------------------
from __future__ import unicode_literals
from ast import Continue


def board(n):
    """Kataskeuastis board (pinaka paixnidiou).

    n -- parametros diastasis pinaka

    Kataskeuazei anaparastasi pinaka paixnidiou me n x n kelia, opou
    kanena keli den einai zwntano arxika. 

    O pinakas anaparistatai ws le3iko (dict) me n * n stoixeia.
    Ka8e keli antistoixei se ena stoixeio me key (kleidi) to tuple (i,j), 
    opou i o ari8mos grammis kai j o ari8mos stilis pou brisketai to keli. 
    (H ari8misi grammwn kai sthlwn einai apo 0 ews n-1. To panw aristera keli
    brisketai sto (0,0).)
    H timi (value) tou stoixeiou einai True 'h False analoga 
    ean to keli einai zwntano 'h oxi.

    Paradeigmata:

    >>> game = board(3)
    >>> len(game)
    9
    >>> game
    {(0, 0): False, (0, 1): False, (0, 2): False, (1, 0): False, (1, 1): False, (1, 2): False, (2, 0): False, (2, 1): False, (2, 2): False}
    >>> game[2,1]
    False
    """
    """GRAPSTE TON KWDIKA SAS EDW."""

    # Με dictionary comprehension:
    tableux = {(row_count, column_count): False for row_count in range(0,n) for column_count in range(0,n)}
    
    # Με for loop:
    #tableux = {}
    #for i in range(0,n):
    #    for j in range(0, n):
    #        tableux[(i,j)] = False
    
    return tableux

#---------------------- Askisi 2 ------------------------
def is_alive(board, p):
    """Elegxei ean ena keli einai zwntano.

    board -- o pinakas paixnidiou opou brisketai to keli
    p -- h 8esh tou keliou.

    To orisma p einai tuple tis morfis (i,j).
    Epistrefei True ean to keli einai zwntano, alliws False.

    Paradeigma:

    >>> is_alive(board(4), (3,2))
    False
    """
    """GRAPSTE TON KWDIKA SAS EDW."""
    
    if board[p] is True:
        return True
    else:
        return False


def set_alive(board, p, alive):
    """Dimioyrgei 'h afairei zwi apo ena keli.

    board -- o pinakas paixnidiou opou brisketai to keli
    p -- h 8esh tou keliou (tuple tis morfis (i,j))
    alive -- logiki timi.

    To keli ginetai zwntano ean h alive einai True. An h alive einai False,
    to keli pe8ainei.

    Paradeigmata:

    >>> game = board(4)
    >>> is_alive(game, (3,2))
    False
    >>> set_alive(game, (3,2), True)
    >>> is_alive(game, (3,2))
    True
    >>> set_alive(game, (3,2), False)
    >>> is_alive(game, (3,2))
    False
    """
    """GRAPSTE TON KWDIKA SAS EDW."""
    
    board[p] = alive
    return 


    
def get_size(board):
    """Mege8os pinaka paixnidiou.

    board -- pinakas paixnidiou.

    Epistrefei n (timi int) ean to board anaparista pinaka paixnidiou n x n.

    Paradeigmata:

    >>> get_size(board(4))
    4
    >>> get_size(board(10))
    10
    """
    """GRAPSTE TON KWDIKA SAS EDW."""
    
    from math import sqrt
    return int(sqrt(len(board)))



#---------------------- Askisi 3 ------------------------
def copy_board(board):
    """Kataskeui antigrafou pinaka paixnidiou.

    board -- pinakas paixnidiou.

    Epistrefei ena neo pinaka paixnidiou pou einai antigrafo tou board.

    Paradeigmata:

    >>> game = board(10)
    >>> set_alive(game, (4,7), True)
    >>> game2 = copy_board(game)
    >>> game2 is game
    False
    >>> is_alive(game2, (4,7))
    True
    """
    """GRAPSTE TON KWDIKA SAS EDW."""
    
    tableux_copy = dict(board)
    return tableux_copy


#---------------------- Askisi 4 ------------------------
def get_iterator(board):
    """Iterator gia sarwsi stoixeiwn pinaka paixnidiou.

    board -- pinakas paixnidiou.

    Epistrefei iterator pou dinei ta kelia tou board arxizontas apo 
    ta kelia tis grammis 0: (0,0), (0,1), (0,2),..., meta akolou8oun ta 
    kelia tis grammis 1: (1,0), (1,1), (1,2),... ktl. mexri na e3antli8oun
    ola ta kelia. Gia ka8e keli, o iterator epistrefei tin 8esi tou kai
    logiki timi True 'h False analogws ean einai zwntano 'h oxi.

    Paradeigma:

    >>> game = board(3)
    >>> set_alive(game, (2, 1), True)
    >>> for cell in get_iterator(game):
    ...     print(cell)
    ... 
    ((0, 0), False)
    ((0, 1), False)
    ((0, 2), False)
    ((1, 0), False)
    ((1, 1), False)
    ((1, 2), False)
    ((2, 0), False)
    ((2, 1), True)
    ((2, 2), False)
    """
    """GRAPSTE TON KWDIKA SAS EDW."""
    
    return board.items()

    # Άλλος τρόπος (ψιλοχαζός)
    #cells = []
    #for item in board.items():
        #cells.append(item)
    #return tuple(cells)


#---------------------- Askisi 5 ------------------------
def print_board(board):
    """Emfanizei pinaka paixnidiou.

    board -- pinakas paixnidiou.

    Emfanizei ton pinaka paixnidiou board opou me mauro tetragwno 
    (xaraktiras unicode 11035 'h xaraktiras 'x' and den emfanizetai swsta) 
    dinontai ta zwntana kelia, kai me aspro (xaraktiras unicode 11036 'h
    keno ' ' an den emfanizetai swsta) ta pe8amena. 
    To panw aristera keli einai auto sti 8esi (0,0).

    Paradeigma:

    >>> game = board(5)
    >>> set_alive(game, (0,0), True)
    >>> set_alive(game, (2,2), True)
    >>> set_alive(game, (4,4), True)
    >>> set_alive(game, (3,4), True)
    >>> set_alive(game, (0,4), True)
    >>> print_board(game)
    ⬛⬜⬜⬜⬛
    ⬜⬜⬜⬜⬜
    ⬜⬜⬛⬜⬜
    ⬜⬜⬜⬜⬛
    ⬜⬜⬜⬜⬛
    """ 
    """GRAPSTE TON KWDIKA SAS EDW."""   
    
    length = get_size(board)
    for row in range(length) :
        for column in range(length):
            if board[row, column]:
                print(chr(11035), end="")
            else:
                print(chr(11036), end="") 
        print("")

#---------------------- Askisi 6 ------------------------
def neighbors(p):
    """Geitonika kelia.

    p -- 8esh keliou (tuple tis morfis (i,j)).

    Epistrefei synolo (set) pou periexontai oi 8eseis twn 8 geitonikwn
    keliwn tou p. Sto epistrefomeno synolo den periexetai to keli p.

    Paradeigmata:

    >>> neighbors((3,2)) == {(3, 3), (3, 1), (2, 1), (2, 3), (4, 3), (2, 2), (4, 2), (4, 1)}
    True
    >>> neighbors((0,0)) == {(0, 1), (-1, 1), (-1, 0), (-1, -1), (0, -1), (1, 0), (1, -1), (1, 1)}
    True
    >>> neighbors((0,10)) == {(-1, 9), (1, 10), (-1, 11), (0, 11), (-1, 10), (1, 9), (0, 9), (1, 11)}
    True
    """
    """GRAPSTE TON KWDIKA SAS EDW."""
    
    neighborhood = set()
    row, column = p
    
    for neighbor_row in range(-1, 2):
        neighboring_row = row + neighbor_row
        for neighbor_column in range(-1, 2):
            neighboring_column = column + neighbor_column
            cell = (neighboring_row, neighboring_column)
            neighborhood.add(cell)
    neighborhood.remove(p)
    
    return neighborhood
    
#---------------------- Askisi 7 ------------------------
def place_blinker(board, p = (0,0)):
    """Topo8etisi blinker.

    board -- pinakas paixnidiou.
    p -- keli topo8etisis (tuple (i,j) me proka8orismeni timi (0,0))

    Topo8etei 3 zwntanous organismous sto board 
    se geitonika kelia tis idias stilis, arxizontas apo ti 8esi p kai
    proxwrontas stis parakatw grammes.

    Paradeigmata:
    
    >>> game = board(5)
    >>> place_blinker(game)
    >>> print_board(game)
    ⬛⬜⬜⬜⬜
    ⬛⬜⬜⬜⬜
    ⬛⬜⬜⬜⬜
    ⬜⬜⬜⬜⬜
    ⬜⬜⬜⬜⬜
    >>> place_blinker(game, (1,2))
    >>> print_board(game)
    ⬛⬜⬜⬜⬜
    ⬛⬜⬛⬜⬜
    ⬛⬜⬛⬜⬜
    ⬜⬜⬛⬜⬜
    ⬜⬜⬜⬜⬜
    >>> place_blinker(game, (4,4))
    >>> print_board(game)
    ⬛⬜⬜⬜⬜
    ⬛⬜⬛⬜⬜
    ⬛⬜⬛⬜⬜
    ⬜⬜⬛⬜⬜
    ⬜⬜⬜⬜⬛
    """
    """GRAPSTE TON KWDIKA SAS EDW."""

    row, column = p
    for next_row in range(row, row+3):
        cell = (next_row, column)
        if cell not in board.keys(): break
        set_alive(board, cell, True)
    return
        


def place_glider(board, p = (0,0)):
    """Topo8etisi glider.

    board -- pinakas paixnidiou.
    p -- keli topo8etisis (tuple (i,j) me proka8orismeni timi (0,0))

    Topo8etei 5 zwntanous organismous sto board se sximatismo glider
    arxizontas apo tin topo8esia p, opws fainetai sta parakatw paradeigmata. 
    Simeiwste oti to idio to keli p den einai zwntano.
    
    Paradeigmata:

    >>> game = board(7)
    >>> place_glider(game)
    >>> print_board(game)
    ⬜⬜⬛⬜⬜⬜⬜
    ⬛⬜⬛⬜⬜⬜⬜
    ⬜⬛⬛⬜⬜⬜⬜
    ⬜⬜⬜⬜⬜⬜⬜
    ⬜⬜⬜⬜⬜⬜⬜
    ⬜⬜⬜⬜⬜⬜⬜
    ⬜⬜⬜⬜⬜⬜⬜
    >>> place_glider(game, (3,3))
    >>> print_board(game)
    ⬜⬜⬛⬜⬜⬜⬜
    ⬛⬜⬛⬜⬜⬜⬜
    ⬜⬛⬛⬜⬜⬜⬜
    ⬜⬜⬜⬜⬜⬛⬜
    ⬜⬜⬜⬛⬜⬛⬜
    ⬜⬜⬜⬜⬛⬛⬜
    ⬜⬜⬜⬜⬜⬜⬜
    """
    """GRAPSTE TON KWDIKA SAS EDW."""
    
    row, column = p
    keys = board.keys()
    row_index = row
    column_index = column +2

    if (row_index, column_index) in keys:
        set_alive(board,(row_index,column_index), True)
    
    row_index = row + 1
    for column_shift in range(0, 3, 2):
        column_index = column + column_shift
        if (row_index, column_index) in keys:
            set_alive(board,(row_index,column_index), True)

    row_index = row + 2
    for column_shift in range(1, 3):
        column_index = column + column_shift
        if (row_index, column_index) in keys:
            set_alive(board,(row_index,column_index), True)
            
    return

#---------------------- Askisi 8 ------------------------
def tick(board):
    """Proxwraei to paixnidi kata ena bima stin epomeni genea.

    board -- pinakas paixnidiou.

    Allazei ton pinaka board proxwrontas mia genea, 
    symfwna me tous kanones tou Game of Life.

    Paradeigma:

    >>> game = board(6)
    >>> place_glider(game)
    >>> place_blinker(game, (3,4))
    >>> print_board(game)
    ⬜⬜⬛⬜⬜⬜
    ⬛⬜⬛⬜⬜⬜
    ⬜⬛⬛⬜⬜⬜
    ⬜⬜⬜⬜⬛⬜
    ⬜⬜⬜⬜⬛⬜
    ⬜⬜⬜⬜⬛⬜
    >>> tick(game)
    >>> print_board(game)
    ⬜⬛⬜⬜⬜⬜
    ⬜⬜⬛⬛⬜⬜
    ⬜⬛⬛⬛⬜⬜
    ⬜⬜⬜⬛⬜⬜
    ⬜⬜⬜⬛⬛⬛
    ⬜⬜⬜⬜⬜⬜
    """
    """GRAPSTE TON KWDIKA SAS EDW."""
    
    new_board = copy_board(board)
    
    alive_cells = [cell for cell in board.keys() if is_alive(board,cell) is True]  # λίστα από tuples όλων των ζωντανών κελιών του board.
 
    #alive_neighborhood = [neighbor for neighbor in neighbors(cell)for cell in board.keys if board[cell] is True]
    #dead_neighborhood = [neighbor for neighbor in neighbors(cell) for cell in board.keys if board[cell] is False]
    
    #n = get_size(board)
    
    living = [([],keli) for keli in alive_cells]  # Αρχικοποίηση λίστας που θα χρησιμοποιηθεί για την οργάνωση των ζωντανών κελιών.
                                                  # Θα αποτελείται από tuples που θα περιέχουν από μία λίστα και ένα tuple.
                                                  # Η λίστα με τη σειρά της θα περιέχει τα γειτονικά κελιά ενός ζωντανού κελιού σε μορφή tuples,
                                                  # ενώ το tuple (keli) θα περιέχει τις συντεταγμένες του συγκεκριμένου ζωντανού κελιού.
                                                  
    for index_keli, keli in enumerate(alive_cells):                # keli -> συντεταγμένες ζωντανού κελιού [tuple]
                                                                   # με ένα συγκεκριμένο πλήθος ζωντανών γειτονικών κελιών  
        for coordinates in neighbors(keli): # coordinates -> συντεταγμένες [tuple] γειτονικών κελιών ενός ζωντανού κελιού
            # Αν κάποιο κελί είναι νεκρό ή δεν υπάρχει στον πίνακα (δηλαδή, is_alive(new_board, coordinates) is False) το αγνοεί και πάει στο επόμενο.
            if coordinates in board.keys() and is_alive(board, coordinates) is True :               
                living[index_keli][0].append(coordinates) 
            
        
        zoi = living[index_keli][0]             # zoi -> λίστα που περιέχει τα ζωντανά γειτονικά κελιά του ζωντανού κελιού keli
        if len(zoi) < 2:                        # Άρα len(zoi) = το πλήθος των ζωντανών γειτονικών κελιών ενός ζωντανού κελιού
            set_alive(new_board, keli, False)   # keli -> συντεταγμένες ζωντανού κελιού [tuple]
                                                  
        elif len(zoi) == 2 or len(zoi) == 3:  
            set_alive(new_board, keli, True)
            
        elif len(zoi) > 3:
            set_alive(new_board, keli, False)
    
    dead_cells  = [cell for cell in board.keys() if is_alive(board,cell) is False] # λίστα από tuples όλων των νεκρών κελιών του board.
    
    dead = [([],keli) for keli in dead_cells] # Αρχικοποίηση λίστας που θα χρησιμοποιηθεί για την οργάνωση των νεκρών κελιών.
                                              # Θα αποτελείται από tuples που θα περιέχουν από μία λίστα και ένα tuple.
                                              # Η λίστα με τη σειρά της θα περιέχει τα γειτονικά κελιά ενός νεκρού κελιού σε μορφή tuples,
                                              # ενώ το tuple -> keli θα περιέχει τις συντεταγμένες του συγκεκριμένου νεκρού κελιού.
                                               
    for index_keli, keli in enumerate(dead_cells):                 # keli -> συντεταγμένες νεκρού κελίού
        
        for coordinates in neighbors(keli): # coordinates [tuple] -> συντεταγμένες γειτονικών κελιών ενός νεκρού κελιού [tuple]
            
           if coordinates in board.keys() and is_alive(board, coordinates) is True:            
                dead[index_keli][0].append(coordinates)
        
        thanos = dead[index_keli][0]
                                                    # thanos -> λίστα που περιέχει τα ζωντανά γειτονικά κελιά του νεκρού κελιού keli
        if len(thanos) == 3:                        # Άρα len(thanos) = το πλήθος των ζωντανών γειτονικών κελιών ενός νεκρού κελιού                                        
            set_alive(new_board, keli, True)        # keli -> συντεταγμένες νεκρού κελιού [tuple]
                                                    # με ένα συγκεκριμένο πλήθος ζωντανών γειτονικών κελιών
    for key in new_board.keys(): 
        board[key] = new_board[key]                 # Μεταγραφή των αποτελεσμάτων από το (αντίγραφο) λεξικό εργασίας, στο αρχικό λεξικό
                                                    # (in place) για να ενημερωθεί αντίστοιχα το λεξικό που αποτελεί το όρισμα εισόδου 
    

#---------------------- Askisi 9 ------------------------

if __name__ == '__main__':
    """Paizei to paixnidi tis zwis gia 100 genies.

    generations -- ari8mos bimatwn.

    Paizei to paixnidi gia mia sygkekrimeni arxiki topo8etisi, gia 
    100 genees. O pinakas tou paixnidiou emfanizetai se  ka8e bima.
    Afiste toulaxiston 2 kenes grammes anamesa se diadoxikous pinakes.
    """

    # Arxikos pinakas
    game = board(10)
    place_blinker(game, (1,2))
    place_glider(game, (2,4))

    from time import sleep

    """GRAPSTE TON KWDIKA SAS APO KATW."""

    gen_count = 1
    print_board(game)
    print("")
    while gen_count<=100:
        print("")
        tick(game)
        print_board(game)
        print("")
        sleep(1.5)
        gen_count += 1
