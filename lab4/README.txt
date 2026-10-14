PaymentService phụ thuộc vào PaymentStrategy và ủy quyền cho strategy.
Main thay đổi strategy khi chạy mà không sửa logic của PaymentService.
Refundable được tách riêng để những phương thức không hỗ trợ hoàn tiền
không phải triển khai refund. IspDemo minh họa phần mở rộng này.