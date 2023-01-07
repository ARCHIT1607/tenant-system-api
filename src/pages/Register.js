import React, { useState } from "react";
import Container from "react-bootstrap/Container";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/esm/FloatingLabel";
import { useNavigate } from "react-router-dom";
import "../css/Register.css";
function Register() {
  const [validated, setValidated] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    } else {
      navigate("userDashboard");
    }
    setValidated(true);
  };

  return (
    <>
      <Container id="RegisterContainer">
        <Form noValidate validated={validated} onSubmit={handleSubmit}>
          <h2 className="mb-3 ">Register</h2>
          <Row className="g-2 mb-3">
            <Col md>
              <FloatingLabel
                controlId="floatingInputGrid"
                label="Email address"
              >
                <Form.Control
                  type="email"
                  required
                  placeholder="name@example.com"
                  style={{ width: "30rem" }}
                />
                <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                <Form.Control.Feedback type="invalid">
                  email is not correct
                </Form.Control.Feedback>
              </FloatingLabel>
            </Col>
            <Col md>
              <FloatingLabel controlId="floatingPassword" label="Password">
                <Form.Control
                  required
                  type="password"
                  placeholder="Password"
                  style={{ width: "30rem" }}
                  pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                />
                <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                <Form.Control.Feedback type="invalid">
                  password criteria not met yet!!
                </Form.Control.Feedback>
              </FloatingLabel>
            </Col>
          </Row>

          <Row className="mb-3">
            <Col md>
              <FloatingLabel controlId="floatingTextarea2" label="Address">
                <Form.Control
                  as="textarea"
                  required
                  placeholder="Address"
                  min={10}
                  style={{ height: "100px", width: "100%" }}
                />
                <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                <Form.Control.Feedback type="invalid">
                  please enter address
                </Form.Control.Feedback>
              </FloatingLabel>
            </Col>
          </Row>

          <Row className="g-3 mb-3">
            <Col md>
              <FloatingLabel
                controlId="floatingSelect"
                label="Works with selects"
              >
                <Form.Select
                  aria-label="Floating label select example"
                  style={{ width: "20rem" }}
                  required
                >
                  <option value="detached">detached</option>
                  <option value="semi-detached">semi-detached</option>
                  <option value="terraced">terraced</option>
                  <option value="flat">flat</option>
                  <option value="cottage">cottage</option>
                  <option value="bungalow and mansion">
                    bungalow and mansion
                  </option>
                </Form.Select>
              </FloatingLabel>
            </Col>
            <Col md>
              <FloatingLabel
                controlId="floatingInputGrid"
                label="Number of bedrooms"
              >
                <Form.Control
                  required
                  type="number"
                  min={0}
                  placeholder="Number of bedrooms"
                  style={{ width: "20rem" }}
                />
                <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                <Form.Control.Feedback type="invalid">
                  invalid number
                </Form.Control.Feedback>
              </FloatingLabel>
            </Col>
            <Col md>
              <FloatingLabel
                controlId="floatingInputGrid"
                label="Energy voucher code (EVC)"
              >
                <Form.Control
                  required
                  type="text"
                  placeholder="Energy voucher code (EVC)"
                  style={{ width: "20rem" }}
                />
                <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                <Form.Control.Feedback type="invalid">
                  invalid number
                </Form.Control.Feedback>
              </FloatingLabel>
            </Col>
          </Row>
          <Button variant="primary" type="submit">
            Submit
          </Button>
        </Form>
      </Container>
    </>
  );
}

export default Register;