import sys

def main():
    liste =sys.argv[1]
    bubble_txt =sys.argv[2]
    insertion_txt = sys.argv[3]

    bubble_file = open(bubble_txt, 'w')
    insertion_file = open(insertion_txt, 'w')

    with open(liste, 'r') as liste_file:
        liste_content = liste_file.read()
        liste = [int(item) for item in liste_content.split()]

    if is_sorted(liste):
        print("Already sorted!")
        bubble_file.write("Already sorted!")
        insertion_file.write("Already sorted!")
    else:
        bubble_sort(liste, bubble_file)
        insertion_sort(liste, insertion_file)

    bubble_file.close()
    insertion_file.close()

def is_sorted(liste):
    for i in range(len(liste) - 1):
        if liste[i] > liste[i + 1]:
            return False
    return True

def bubble_sort(liste, bubble_file):
    for i in range(len(liste)):
        swapped = False
        for j in range(len(liste) - 1):
            if liste[j] > liste[j + 1]:
                liste[j], liste[j + 1] = liste[j + 1], liste[j]
                swapped = True
        if swapped:
            bubble_file.write(f"Pass {i+1}:{liste}\n")
        if not swapped:
            break

def insertion_sort(liste, insertion_file):
    for i in range(1, len(liste)):
        keypoint = liste[i]
        compared_index = i - 1
        while compared_index >= 0 and liste[compared_index] > keypoint:
            liste[compared_index + 1] = liste[compared_index]
            compared_index = compared_index - 1
        liste[compared_index + 1] = keypoint
        insertion_file.write(f"Pass {i}: {liste}\n")

if __name__ == "__main__":
    main()