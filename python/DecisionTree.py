def entropy(a, b) :
    pa = a/(a+b)
    pb = b/(a+b)
    arr = [pa, pb]
    return arr


print(entropy(2,3))