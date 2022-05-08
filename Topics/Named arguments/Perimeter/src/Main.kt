fun perimeter(x1: Double, y1: Double, x2: Double, y2: Double, x3: Double, y3: Double, x4: Double = x3, y4: Double = y3): Double {
    return Math.hypot(x1 - x2, y1 - y2) + Math.hypot(x2 - x3, y2 - y3) + Math.hypot(x3 - x4, y3 - y4) + Math.hypot(x1 - x4, y1 - y4)
}