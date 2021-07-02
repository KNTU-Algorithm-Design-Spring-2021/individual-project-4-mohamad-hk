if __name__ == "__main__":
    weight_list = []
    k = int(input("Enter number of trucks : "))
    n = int(input("Enter number of elements : "))
    print("Enter every element weight in one line : ")
    for i in range(n):
        element_number_i_wight = float(input())
        weight_list.append(element_number_i_wight)

    weight_list.sort(reverse=True)
    truck_set = set()
    for i in range(k):
        truck_set.add((0, i))

    for i in range(n):
        minimum_truck_weight = min(truck_set)[0]
        minimum_truck_weight_number = min(truck_set)[1]
        truck_set.remove((minimum_truck_weight, minimum_truck_weight_number))
        truck_weight_after_add_i_element = minimum_truck_weight + weight_list[i]
        truck_set.add((truck_weight_after_add_i_element, minimum_truck_weight_number))
    print("Answer is : ", max(truck_set)[0])
    print(truck_set)
