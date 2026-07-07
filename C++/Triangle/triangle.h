#ifndef triangle_H
#define triangle_H

/**
* Dylan Brooks
* CSCI 362
* August 25, 2023
*
* This program creates a triangle object.
*/

class triangle{
private:
    float m_side1;
    float m_side2;
    float m_side3;

public:
    // Creates a default triangle with all sides set to 1
    triangle(){
        m_side1 = 1;
        m_side2 = 1;
        m_side3 = 1;

    }

    // Creates a triangle with all sides set to num
    triangle(float num){
        m_side1 = num;
        m_side2 = num;
        m_side3 = num;
    }

    // Creates a triangle with side 1 = x, side 2 = y, and side 3 = z
    triangle(const float &x, const float &y, const float &z) {
        m_side1 = x;
        m_side2 = y;
        m_side3 = z;
    }

    // Returns the value of m_side1
    float get_side1() const {
        return m_side1;
    }

    // Returns the value of m_side2
    float get_side2() const {
        return m_side2;
    }

    // Returns the value of m_side3
    float get_side3() const {
        return m_side3;
    }

    // Sets m_side1 = num
    void set_side1(const float& num) {
        m_side1 = num;
    }

    // Sets m_side2 = num
    void set_side2(const float& num) {
        m_side2 = num;
    }

    // Sets m_side3 = num
    void set_side3(const float& num) {
        m_side3 = num;
    }

    // Calculates the perimeter of the triangle
    float perimeter() const {
        return m_side1 + m_side2 + m_side3;
    }

    // Finds the value of the largest side of the triangle
    float largest_side() const {
        if (m_side1 >= m_side2 && m_side1 >= m_side3) {
            return m_side1;
        } else if (m_side2 >= m_side1 && m_side2 >= m_side3) {
            return m_side2;
        } else {
            return m_side3;
        }
    }

    // Returns true if the triangle is right, fakse otherwise
    bool is_right() const {
        if (largest_side() == m_side1) {
            if ((m_side1*m_side1) == (m_side2*m_side2 + m_side3*m_side3)) {
                return true;
            } else {
                return false;
            }
        } else if (largest_side() == m_side2) {
            if ((m_side2*m_side2) == (m_side1*m_side1 + m_side3*m_side3)) {
                return true;
            } else {
                return false;
            }
        } else {
            if ((m_side3*m_side3) == (m_side2*m_side2 + m_side1*m_side1)) {
                return true;
            } else {
                return false;
            }
        }
    }


};

#endif